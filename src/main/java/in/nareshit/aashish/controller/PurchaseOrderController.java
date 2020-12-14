package in.nareshit.aashish.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.nareshit.aashish.model.PurchaseDtl;
import in.nareshit.aashish.model.PurchaseOrder;
import in.nareshit.aashish.service.IPartService;
import in.nareshit.aashish.service.IPurchaseOrderService;
import in.nareshit.aashish.service.IShipmentTypeService;
import in.nareshit.aashish.service.IWhUserTypeService;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {
	
	@Autowired
	private IPurchaseOrderService service;
	
	//ParentController----<>IChildService
	@Autowired
	private IShipmentTypeService shipmentService;
	@Autowired
	private IWhUserTypeService whuserService;
	@Autowired
	private IPartService partService;
	
	//for Register Page drop-down
	//define one private method so we can re-use it
	//call this private method for Register, Edit pages and save operation also.
	private void addDynamicUiComponents(Model model) {
		Map<Integer, String> map1 = shipmentService.getShipmentIdAndCodeByEnabled("Yes");
		model.addAttribute("shipmenttypes", map1);
		Map<Integer, String> map2 = whuserService.getWhUserIdAndCodeByType("Vendor");
		model.addAttribute("vendors", map2);
	}
	
	//1. show register page
	/**
	 * This method work is to show only "PurchaseOrderRegister" register 
	 * page
	 * @param model is used to send data from Controller to Ui
	 * @return
	 */
	@GetMapping("/register")
	public String showRegPage(Model model) {
		//form backing object
		PurchaseOrder po = new PurchaseOrder();
		po.setStatus("OPEN");
		model.addAttribute("purchaseOrder", po);
		//where exactly we want drop-down, call this method
		addDynamicUiComponents(model);
		return "PurchaseOrderRegister";		
	}
	//2. save PurchaseOrder onclick submit
	/**
	 * onClick of CREATE PURCHASE ORDER button in PurchaseOrderRegister
	 * page, one record or one object data is saved in the database.
	 * @param purchaseOrder reads the form data in the form of ModelAttribute.
	 * @param model is used to send data from Controller to Ui.
	 * @return the "PurchaseOrderRegister" page
	 */
	@PostMapping("/save")
	public String savePurchaseOrder(
			@ModelAttribute PurchaseOrder purchaseOrder,
			Model model
			) {
		//call to service layer method
		Integer id = service.savePurchaseOrder(purchaseOrder);
		
		String message = new StringBuffer().append("PurchaseOrder with id '")
				.append(id).append("' is saved").toString();
		
		model.addAttribute("message", message);
		//sending form backing object to the UI
		PurchaseOrder po = new PurchaseOrder();
		po.setStatus("OPEN");
		model.addAttribute("purchaseOrder", po);
		//where exactly we want drop-down, call this method
		addDynamicUiComponents(model);
		return "PurchaseOrderRegister";
	}
	//3. Display Purchase Orders
	/**
	 * This method will fetch all the records present in the database
	 * related to PurchaseOrder.
	 * @param model is used to send data from Controller to Ui
	 * @return "PurchaseOrderData" page
	 */
	@GetMapping("/all")
	public String showAllPurchaseOrderData(Model model) {
		//call service layer method
		List<PurchaseOrder> list = service.getAllPurchaseOrders();
		//send this list data to UI using Model memory 
		model.addAttribute("list", list);
		return "PurchaseOrderData";
	}
	
	//4. ---- Methods for Screen-2 i.e. Purchase Order Parts/Details Page ----
	
	private void addDynamicUiComponentsForParts(Model model) {
		Map<Integer, String> map1 = partService.getPartIdandCode();
		model.addAttribute("parts", map1);
	}
	/**
	 * This method is showing the output of Screen#2 by fetching one PurchaseOrder
	 * object by id.
	 * It is displayed when we click on ADD PARTS button from
	 * display data page of Screen#1's data page.
	 * And even after adding new Part or after removing the Part
	 * same page is loaded.
	 * @param id reads the value of id from Ui
	 * @param model is used to send data from Controller to Ui.
	 * @return the Ui page with name "PurchaseOrderParts".
	 */
	@GetMapping("/parts")
	public String showPoPartsPage(
			@RequestParam Integer id, //PO id
			Model model
			) {
		//-----section#1-----
		//Get PurchaseOrder by id
		/*
		 * We have id i.e. Order Id, we want to display Order Code and
		 * Order Status. So we can get one PurchaseOrder object using id
		 * that contains orderCode and status.
		 * Then send this fetched PurchaseOrder object to Ui and display
		 * using thymeleaf code i.e. ${po.orderCode} and ${po.status}. 
		 */
	    PurchaseOrder po = service.getOnePurchaseOrder(id);
	    model.addAttribute("po", po);
	    
	    
	    //send form backing object
	    model.addAttribute("purchaseDtl", new PurchaseDtl());
	    
	    //-----section#2-----
	    //dynamic drop-down
	    addDynamicUiComponentsForParts(model);
	    
	    //-----section#4-----
	    //show Parts added to Purchase Order based on orderId
	    List<PurchaseDtl> list = service.getPurchaseDtlsByOrderId(id); //order id
	    model.addAttribute("dtlsList", list); //send to Ui
		return "PurchaseOrderParts";
	}	
	
	/**
	 * onClick Add button in PO Screen#2,
	 * Read form data as PurchaseDtl,save into Db using
	 * service layer method and then,
	 * Redirect to same UI page with /parts?id=<orderId>
	 */
	//-----section#3-----
	@PostMapping("/add")
	public String addPart(@ModelAttribute PurchaseDtl purchaseDtl) 
	{
		service.savePurchaseDtl(purchaseDtl);
		//from PurchaseDtl -> get Order(PurchaseOrder) -> from order get Id (order id)
		return "redirect:parts?id="+purchaseDtl.getOrder().getId(); //PO id
	}
	/**
	 * On click of Remove button of PurchaseOrder's Screen#2,
	 * this method is called with two inputs @param dtlId and
	 * @param orderId.
	 * It will remove one Part detail using dtlId and then redirect
	 * to the same page using orderId.
	 * @return to the same page by redirecting to the path /parts related
	 * method.
	 */
	@GetMapping("/remove")
	public String removePart(
			@RequestParam Integer dtlId,
			@RequestParam Integer orderId
			) {
		//call service layer method to delete the purchase detail record by id
		service.removePurchaseDtl(dtlId);
		return "redirect:parts?id="+orderId;  //id is PO id
	}
		
}
