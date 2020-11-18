package in.nareshit.aashish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.nareshit.aashish.model.OrderMethod;
import in.nareshit.aashish.service.IOrderMethodService;

@Controller
@RequestMapping("/ordermethod")
public class OrderMethodController {
	
	@Autowired
	private IOrderMethodService service;
	
	/**
	 * 1. show register page on enter of 
	 * 	  /register URL of GET type
	 * @return OrderMethodRegister page
	 */
	@GetMapping("/register")
	public String showRegister(Model model) {
		//Form backing object//must written before return statement
		model.addAttribute("order", new OrderMethod());
		return "OrderMethodRegister";
	}
	/**
	 * 2. This method will read form data as ModelAttribute.
	 *    It performs save() operation using service and returns 
	 *    generated ID.
	 *    Construct one message and send to UI using Model memory.
	 *    On entering URL: /save with Type : POST
	 * @param orderMethod reads form data
	 * @param model is used to send data to UI
	 * @return the same page i.e. OrderMethodRegister
	 */
	@PostMapping("/save")
	public String saveOrderMethod(
			@ModelAttribute OrderMethod orderMethod,
			Model model
			) {
		Integer id = service.saveOrderMethod(orderMethod);
		String message = new StringBuffer().append("OrderMethod with id '")
				.append(id).append("' Saved").toString();
		
		model.addAttribute("msg", message);
		//form backing object
		model.addAttribute("order", new OrderMethod());
		return "OrderMethodRegister";
	}
	/**
	 * 3. This method gets data from database using service 
	 * 	  and send to UI using model. It is called when 
	 *    Path: /all with type GET is requested.
	 * @param model is used to send data from controller to UI.
	 * @return the page i.e. OrderMethodData
	 */
	@GetMapping("/all")
	public String ShowAllOrderMethods(
			Model model
			) {
		//call to service layer method
		List<OrderMethod> list = service.getAllOrderMethods();
		//send data to UI
		model.addAttribute("list", list);
		return "OrderMethodData";		
	}
	/**
	 * 4. This method read @param id from dynamic path from UI using 
	 *    @RequestParam and makes a call to service layer method to
	 *    delete the data. Create one new message and send it to the same
	 *    UI page using the @param model
	 *    Path: /delete?id=val   (GET type)
	 *     	  
	 * @param id value is given by UI page
	 * @param model is used to send data to UI
	 * @return the same page i.e. OrderMethodData
	 */
	@GetMapping("/delete")
	public String deleteOrderMethod(
			@RequestParam Integer id,
			Model model
			) {
		//call service layer method
		service.deleteOrderMethod(id);
		//creating a message to send it on to UI
		String message = new StringBuffer().append("OrderMethod with id '")
				.append(id).append("' Deleted").toString();
		//sending message to UI
		model.addAttribute("message", message);
		//again calling service layer method
		 List<OrderMethod> list = service.getAllOrderMethods();
		 //sending List object to the Ui
		 model.addAttribute("list", list);
		return "OrderMethodData";
	}
	/**
	 * 5. This method we have taken here to show Edit page
	 *    onclick of EDIT hyperlink.
	 *    ex- Req: /ordermethod/edit?id=10   ,  Type: GET
	 *    Read object from database using PK(Id) and send it
	 *    to Edit form(UI)
	 *	  @return to the OrderMethodEdit page.
	 */
	@GetMapping("/edit")
	public String showEdit(
			@RequestParam Integer id,
			Model model
			) {
		//calling service layer method
		OrderMethod orderMethod = service.getOneOrderMethod(id);
		//sending OrderMethod object to the UI using Model memory
		model.addAttribute("ordermethod", orderMethod);
		return "OrderMethodEdit";		
	}
	/**
	 * 6. On click of update button, read form data as 
	 *    ModelAttribute uom , send it to database
	 *    using Service layer. Redirect to all.
	 *    [Response.sendRedirect("/all")
	 * @return OrderMethodData page.
	 */
	
	@PostMapping("/update")
	public String updateOrderMethod(
			@ModelAttribute OrderMethod orderMethod,
			Model model
			) {
		//calling service layer method
		service.updateOrderMethod(orderMethod);
		//sending data to UI
		model.addAttribute("message", "OrderMethod '"+orderMethod.getId()+"' Updated");
		model.addAttribute("list", service.getAllOrderMethods());
		return "OrderMethodData";
	}
	

}
