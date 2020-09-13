package in.nareshit.aashish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.aashish.model.Uom;
import in.nareshit.aashish.service.IUomService;

@Controller
@RequestMapping("/uom")
public class UomController {
	
	@Autowired
	private IUomService service;
	
	/**
	 * 1. show register page 
	 *    on enter  /register URL  (GET)
	 */
	@GetMapping("/register")
	public String showReg() {
		return "UomRegister";
	}
	/**
	 * 2. This method will read form data as ModelAttribute.
	 *    It performs save() operation using service and returns generated ID.
	 *    Construct one message and send to UI using Model memory.
	 *    On entering URL: /save with Type : POST
	 */
	
	@PostMapping("/save")
	public String saveUom(
			@ModelAttribute Uom uom, //read form data as an object
			Model model				//send data controller to UI
			) {
		//calling service layer method
		Integer id = service.saveUom(uom);
		String message = new StringBuffer().append("Uom '")
				.append(id).append("' Saved").toString();
		//sending data to UI
		model.addAttribute("message", message);
		return "UomRegister";
	}
	@GetMapping("/all")
	public String showAllUoms(Model model) {
		List<Uom> list = service.getAllUoms();
		model.addAttribute("list", list);
		return "UomData";
		
	}

}
