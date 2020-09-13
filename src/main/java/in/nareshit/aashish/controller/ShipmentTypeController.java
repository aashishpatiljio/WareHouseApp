package in.nareshit.aashish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.aashish.model.ShipmentType;
import in.nareshit.aashish.service.IShipmentTypeService;

@Controller
@RequestMapping("/shipmenttype")
public class ShipmentTypeController {
	
	@Autowired
	private IShipmentTypeService service;
	
	@GetMapping("/register")
	public String showRegister() {
		return "ShipmentTypeRegister";		
	}
	
	@PostMapping("/save")
	public String saveShipmentType(
			@ModelAttribute ShipmentType shipmentType,
			Model model
			) {
		Integer id = service.saveShipmentType(shipmentType);
		String message = new StringBuffer().append("ShipmentType '")
				.append(id).append("' Saved").toString();
		
		model.addAttribute("msg", message);
		return "ShipmentTypeRegister";
		
	}

}
