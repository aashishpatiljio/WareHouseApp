package in.nareshit.aashish.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.aashish.model.Part;
import in.nareshit.aashish.service.IPartService;
import in.nareshit.aashish.service.IUomService;

@Controller
@RequestMapping("/part")
public class PartController {

	@Autowired
	private IPartService service;
	//ParentController----<>IChildService
	@Autowired
	private IUomService uomService;
	
	//define one private method so we can re-use it
	//call this private method for Register, Edit pages and save operation also.
	private void addDynamicUiComponents(Model model) {
		//call Child Service method
		Map<Integer, String> map = uomService.getUomIdAndModel();
		model.addAttribute("uoms", map);
	}
	
	//1. show Part register page
	@GetMapping("/register")
	public String showPartReg(Model model) {
		//where exactly we want drop-down, call this method
		addDynamicUiComponents(model);
		//form-backing object must be written before the return statement
		model.addAttribute("part", new Part());
		return "PartRegister";
	}
	
	//2. save Parts
	@PostMapping("/save")
	public String savePart(
			@ModelAttribute Part part,
			Model model
			) {
		//call to service layer method
		Integer id = service.savePart(part);
		String message = new StringBuffer().append("Part with id '").append(id)
				.append("' Saved").toString();
		
		model.addAttribute("message", message);
		model.addAttribute("part", new Part());
		//where exactly we want drop-down, call this method
		addDynamicUiComponents(model);
		return "PartRegister";
	}
	
	//3. Display parts
	//4. Show Edit page
	//5. Do Update
}
