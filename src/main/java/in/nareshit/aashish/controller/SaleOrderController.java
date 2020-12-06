package in.nareshit.aashish.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.aashish.model.SaleOrder;
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
	
	//define one private method so we can re-use it
	//call this private method for Register, Edit pages and save operation also.
	private void addDynamicUiComponents(Model model) {
		Map<Integer, String> map1 = shipmentService.getShipmentIdAndCodeByEnabled("Yes");
		model.addAttribute("shipmenttypes", map1);
		Map<Integer, String> map2 = whuserService.getWhUserIdAndCodeByType("Customer");
		model.addAttribute("customers", map2);
	}

	// 1. show Register Page
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
}
