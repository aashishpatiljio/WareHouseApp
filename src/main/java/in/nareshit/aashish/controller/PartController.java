package in.nareshit.aashish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.aashish.model.Part;
import in.nareshit.aashish.service.IPartService;

@Controller
@RequestMapping("/part")
public class PartController {

	@Autowired
	private IPartService service;
	
	//1. show Part register page
	@GetMapping("/register")
	public String showPartReg(Model model) {
		//form-backing object must be written before the return statement
		model.addAttribute("part", new Part());
		return "PartRegister";
	}
	//2. save Parts
	//3. Display parts
	//4. Show Edit page
	//5. Do Update
}
