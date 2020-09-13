package in.nareshit.aashish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.aashish.model.OrderMethod;
import in.nareshit.aashish.service.IOrderMethodService;

@Controller
@RequestMapping("/ordermethod")
public class OrderMethodController {
	
	@Autowired
	private IOrderMethodService service;
	
	@GetMapping("/register")
	public String showRegister() {
		return "OrderMethodRegister";
	}
	
	@PostMapping("/save")
	public String saveOrderMethod(
			@ModelAttribute OrderMethod orderMethod,
			Model model
			) {
		Integer id = service.saveOrderMethod(orderMethod);
		String message = new StringBuffer().append("OrderMethod '")
				.append(id).append("' Saved").toString();
		
		model.addAttribute("msg", message);
		return "OrderMethodRegister";
	}

}
