package in.nareshit.aashish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.aashish.model.User;
import in.nareshit.aashish.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	//1. show User register page
	@GetMapping("/register")
	public String showReg() {
		return "UserRegister";
	}
	
	//2. save/add user
	@PostMapping("/save")
	public String saveUser(
			@ModelAttribute User user,
			Model model
			) {
		Integer id = service.saveUser(user);
		String message = new StringBuffer().append("User with id '")
				.append(id).append("' is saved").toString();
		model.addAttribute("message",message);
		return "UserRegister";
	}
	//3. Show all Users
	@GetMapping("/all")
	public String showAllUsers(Model model) {
		//call service layer method
		List<User> list = service.getAllUsers();
		model.addAttribute("list", list);
		return "UserData";
	}

}
















