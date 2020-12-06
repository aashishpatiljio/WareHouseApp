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

import in.nareshit.aashish.model.PurchaseOrder;
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
	
	
	//define one private method so we can re-use it
	//call this private method for Register, Edit pages and save operation also.
	private void addDynamicUiComponents(Model model) {
		Map<Integer, String> map1 = shipmentService.getShipmentIdAndCodeByEnabled("Yes");
		model.addAttribute("shipmenttypes", map1);
		Map<Integer, String> map2 = whuserService.getWhUserIdAndCodeByType("Vendor");
		model.addAttribute("vendors", map2);
	}
	
	//1. show register page
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
	@GetMapping("/all")
	public String showAllPurchaseOrderData(Model model) {
		//call service layer method
		List<PurchaseOrder> list = service.getAllPurchaseOrders();
		//send this list data to UI using Model memory 
		model.addAttribute("list", list);
		return "PurchaseOrderData";
	}
	
	//4. ---- Methods for Screen-2 i.e. Purchase Order Parts/Details Page ----
	@GetMapping("/parts")
	public String showPoPartsPage(
			@RequestParam Integer id,
			Model model
			) {
		//Get PurchaseOrder by id
	    PurchaseOrder po = service.getOnePurchaseOrder(id);
	    model.addAttribute("po", po);
		return "PurchaseOrderParts";
	}
	
	
}
