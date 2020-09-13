package in.nareshit.aashish.controller;

import java.util.List;

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
	
	/**
	 * 1. show register page 
	 *    on enter  /register URL  (GET)
	 */
	@GetMapping("/register")
	public String showRegister() {
		return "ShipmentTypeRegister";		
	}
	/**
	 * 2. This method will read form data as ModelAttribute.
	 *    It performs save() operation using service and returns generated ID.
	 *    Construct one message and send to UI using Model memory.
	 *    On entering URL: /save with Type : POST
	 */
	@PostMapping("/save")
	public String saveShipmentType(
			@ModelAttribute ShipmentType shipmentType,
			Model model
			) {
		//calling service layer method
		Integer id = service.saveShipmentType(shipmentType);
		String message = new StringBuffer().append("ShipmentType '")
				.append(id).append("' Saved").toString();
		//send data to UI
		model.addAttribute("message", message);
		return "ShipmentTypeRegister";		
	}
	
	@GetMapping("/all")
	public String showAllShipmentTypes(Model model) {
		List<ShipmentType> list = service.getAllShipmentTypes();
		model.addAttribute("list", list);
		return "ShipmentTypeData";
	}
	
		

}
