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

import in.nareshit.aashish.model.SaleDtl;
import in.nareshit.aashish.model.SaleOrder;
import in.nareshit.aashish.service.IPartService;
import in.nareshit.aashish.service.ISaleOrderService;
import in.nareshit.aashish.service.IShipmentTypeService;
import in.nareshit.aashish.service.IWhUserTypeService;

@Controller
@RequestMapping("/so")
public class SaleOrderController {

	@Autowired
	private ISaleOrderService service;
	
	//ParentController----<>IChildService
	@Autowired
	private IShipmentTypeService shipmentService;
	@Autowired
	private IWhUserTypeService whuserService;
	@Autowired
	private IPartService partService;
	
	//define one private method so we can re-use it
	//call this private method for Register, Edit pages and save operation also.
	private void addDynamicUiComponents(Model model) {
		Map<Integer, String> map1 = shipmentService.getShipmentIdAndCodeByEnabled("Yes");
		model.addAttribute("shipmenttypes", map1);
		Map<Integer, String> map2 = whuserService.getWhUserIdAndCodeByType("Customer");
		model.addAttribute("customers", map2);
	}

	//1. show register page
	/**
	 * This method work is to show only "SaleOrderRegister" register 
	 * page
	 * @param model is used to send data from Controller to Ui
	 * @return SaleOrderRegister page
	 */
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		// form backing object
		SaleOrder so = new SaleOrder();
		so.setStatus("SALE-OPEN");
		model.addAttribute("saleOrder", so);
		//where exactly we want drop-down, call this method
		addDynamicUiComponents(model);
		return "SaleOrderRegister";
	}
	
	//2. save SaleOrder onclick submit
	/**
	 * onClick of CREATE SALE ORDER button in SaleOrderRegister
	 * page, one record or one object data is saved in the database.
	 * @param saleOrder reads the form data in the form of ModelAttribute.
	 * @param model is used to send data from Controller to Ui.
	 * @return the "SaleOrderRegister" page
	 */
	@PostMapping("/save")
	public String saveSaleOrder(
			@ModelAttribute SaleOrder saleOrder,
			Model model
			) {
		//call service layer method
		Integer id = service.saveSaleOrder(saleOrder);
		String message = new StringBuffer().append("SaleOrder with id '")
				.append(id).append("' saved").toString();
		
		model.addAttribute("message", message);
		// form backing object
		SaleOrder so = new SaleOrder();
		so.setStatus("SALE-OPEN");
		model.addAttribute("saleOrder", so);
		//where exactly we want drop-down, call this method
		addDynamicUiComponents(model);
		return "SaleOrderRegister";
	}
	//3. Display Purchase Orders
	/**
	 * This method will fetch all the records present in the database
	 * related to SaleOrder.
	 * @param model is used to send data from Controller to Ui
	 * @return "SaleOrderData" page
	 */
	@GetMapping("/all")
	public String showAllSaleOrderData(Model model) {
		//call service layer method
		List<SaleOrder> list = service.getAllSaleOrders();
		//send list data to Ui using Model memory
		model.addAttribute("list", list);
		return "SaleOrderData";
	}
	
	//4. ---- Methods for Screen-2 i.e. Sale Order Parts/Details Page ----
	private void addDynamicUiComponentsForParts(Model model) {
		Map<Integer, String> map1 = partService.getPartIdandCode();
		model.addAttribute("parts", map1);
	}
	/**
	 * This method is showing the output of Screen#2 by fetching one SaleOrder
	 * object by id.
	 * It is displayed when we click on ADD PARTS button from
	 * display data page of Screen#1's data page.
	 * And even after adding new Part or after removing the Part
	 * same page is loaded.
	 * @param id reads the value of id from Ui as Request Param
	 * @param model is used to send data from Controller to Ui.
	 * @return the Ui page with name "SaleOrderParts".
	 */
	@GetMapping("/parts")
	public String showSoPartsPage(
			@RequestParam Integer id,
			Model model
			) {
		//-----section#1-----
		//Get PurchaseOrder by id
		/*
		 * We have id i.e. Order Id, we want to display Order Code and
		 * Order Status. So we can get one SaleOrder object using id
		 * that contains orderCode and status.
		 * Then send this fetched SaleOrder object to Ui and display
		 * using thymeleaf code i.e. ${so.orderCode} and ${so.status}. 
		 */
		//call service layer method
		SaleOrder so = service.getOneSaleOrder(id);
		model.addAttribute("so", so);
		
		//send form backing object
	    model.addAttribute("saleDtl", new SaleDtl());
	    
	    //-----section#2-----
	    //dynamic drop-down
	    addDynamicUiComponentsForParts(model);
	    
	    //-----section#4-----
	  //show Parts added to Sale Order based on orderId
	    List<SaleDtl> list = service.getSaleDtlsByOrderId(id);  //orderId
	    model.addAttribute("dtlsList", list); //sending data to Ui
		return "SaleOrderParts";				
	}
	
	//-----section#3-----
	/**
	 * onClick Add button in SO Screen#2,
	 * Read form data as SaleDtl,save into Db using
	 * service layer method and then,
	 * Redirect to same UI page with /parts?id=<orderId>
	 */
	@PostMapping("/add")
	public String addPart(@ModelAttribute SaleDtl saleDtl) {
		service.saveSaleDtl(saleDtl);
		
		return "redirect:parts?id="+saleDtl.getOrder().getId();
	}
	/**
	 * On click of Remove button of SaleOrder's Screen#2,
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
		//call service layer method to delete the sale detail record by dtlId
		service.removeSaleDtl(dtlId);
		
		return "redirect:parts?id="+orderId;
	}
	
}
