package in.nareshit.aashish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.aashish.model.SaleOrder;
import in.nareshit.aashish.service.ISaleOrderService;

@Controller
@RequestMapping("/so")
public class SaleOrderController {

	@Autowired
	private ISaleOrderService service;

	// 1. show Register Page
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		// form backing object
		SaleOrder so = new SaleOrder();
		so.setStatus("SALE-OPEN");
		model.addAttribute("saleOrder", so);
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
		return "SaleOrderRegister";
	}
}
