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
import org.springframework.web.bind.annotation.ResponseBody;

import in.nareshit.aashish.model.WhUserType;
import in.nareshit.aashish.service.IWhUserTypeService;
import in.nareshit.aashish.util.EmailUtil;

@Controller
@RequestMapping("/whusertype")
public class WhUserTypeController {

	@Autowired
	private IWhUserTypeService service;
	@Autowired
	private EmailUtil util;

	/**
	 * 1. show register page on enter /register URL (GET)
	 */
	@GetMapping("/register")
	public String showRegister(Model model) {
		// Form backing object//must written before return statement
		model.addAttribute("whUserType", new WhUserType());
		return "WhUserTypeRegister";
	}
	/**
	 * 2. This method will read form data as ModelAttribute.
	 *    It performs save() operation using service and returns generated ID.
	 *    Construct one message and send to UI using Model memory.
	 *    On entering URL: /save with Type : POST
	 */
	@PostMapping("/save")
	public String saveWhUserType(
			@ModelAttribute WhUserType whUserType,
			Model model
			) {
		//calling Service layer method
		Integer id = service.saveWhUserType(whUserType);
		String message = new StringBuffer().append("WhUserType with '")
				.append(id).append("' Saved").toString();
		
		//on save successful then below logic executed to send an email--
		if(id!=null && id>0) {
			util.sendEmail(whUserType.getUserEmail(), "Welcome WhUSer", "HELLO: "+whUserType.getUserCode());
		}
		//sending message data to UI
		model.addAttribute("message", message);
		//Form backing object
		model.addAttribute("whUserType", new WhUserType());
		return "WhUserTypeRegister";
	}
	/**
	 * 3. This method gets data from database using service 
	 * 	  and send to UI using model. It is called when 
	 *    Path: /all with type GET is requested.
	 * @param model is used to send data from controller to UI.
	 * @return the page i.e. WhUserTypeData
	 */
	@GetMapping("/all")
	public String showAllWhUserTypes(Model model) {
		//call to the Service layer method
		List<WhUserType> list = service.getAllWhUserTypes();
		model.addAttribute("list", list);
		return "WhUserTypeData";
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
	 * @return the same page i.e. UomData
	 */
	@GetMapping("/delete")
	public String deleteWhUserType(
			@RequestParam Integer id,
			Model model
			) {
		//call to Service layer method
		service.deleteWhUserType(id);
		//creating message to send to UI after the deletion of a record
		String message = new StringBuffer().append("WhUserType with '")
				.append(id).append("' deleted").toString();
		//sending message data to UI
		model.addAttribute("message", message);
		/* again call to service layer method to fetch the remaining 
		 * data after deletion of a record.
		 */
		List<WhUserType> list = service.getAllWhUserTypes();
		//sending this above remaining fetched data to UI using Model memory
		model.addAttribute("list", list);
		return "WhUserTypeData";
	}
	/**
	 * 5. This method we have taken here to show Edit page
	 *    onclick of EDIT hyperlink.
	 *    ex- Req: /whusertype/edit?id=10   ,  Type: GET
	 *    Read object from database using PK(Id) and send it
	 *    to Edit form(UI)
	 *	  @return to the WhUserTypeEdit page.
	 */
	@GetMapping("/edit")
	public String showEdit(
			@RequestParam Integer id,
			Model model
			) {
		//call to the Service layer method
		WhUserType whUser = service.getOneWhUserType(id);
		//sending data to UI
		model.addAttribute("whUser", whUser);
		return "WhUserTypeEdit";
	}
	/**
	 * 6. On click of update button, read form data as 
	 *    ModelAttribute, send it to database
	 *    using Service layer. Redirect to all.
	 *    [Response.sendRedirect("/all")
	 * @return WhUserTypeData page.
	 */
	@PostMapping("/update")
	public String updateWhUserType(
			@ModelAttribute WhUserType whUserType,
			Model model
			) {
		//call to service layer method to update the received data
		service.updateWhUserType(whUserType);
		//create message to send to UI after updation of record
		String message = new StringBuffer().append("WhUserType with '")
				.append(whUserType.getId()).append("'Updated").toString();
		
		//now sending this message content to the UI using Model memory
		model.addAttribute("message", message);
		/* After the updation of a record, we have to show all the rest of 
		 * the records with the updated one also on the UI, for that purpose 
		 * again we have to make a call to a service layer method to fetch all
		 * the records and then send the fetched data/records to the UI using  
		 * the Model memory. 
		 */
		List<WhUserType> list = service.getAllWhUserTypes(); //records fetched
		//sending the above fetched data to teh UI using the Model memory
		model.addAttribute("list", list);
		return "WhUserTypeData";
	}
	/**
	 * 7. AJAX VALIDATION Done in UI pages that's why this method is called 
	 *    by AJAX.
	 * 	  This method checks the input value given to UI form is present in
	 *    DataBase or not, if yes then it returns the message(non-empty message)
	 *    to the UI page form, then form will not be submitted successfully,
	 *    if not then this method will return the empty message that means there 
	 *    is no validation error in the form and form will be submitted successfully.
	 *    
	 * 	  If we don't write @ResponseBody then it will expect return 
	 *    type as a page name but this time we  want to  return the 
	 *    data/message that we have generated in the below method to 
	 *    the UI page.
	 * @param mail holds the value it receives from UI page via AJAX call.
	 * @return the message i.e. msg.
	 */
	@GetMapping("/checkMail")
	public @ResponseBody String validateEmailExist(@RequestParam String mail) {
		String msg = "";
		if(service.isWhUserMailIdExist(mail)) {
			msg = mail + ", <b>already exist</b>";
		}
		return msg; 
	}
	
}
