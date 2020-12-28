package in.nareshit.aashish.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import in.nareshit.aashish.model.Uom;
import in.nareshit.aashish.service.IUomService;
import in.nareshit.aashish.util.UomUtil;
import in.nareshit.aashish.view.UomExcelView;
import in.nareshit.aashish.view.UomPdfView;

@Controller
@RequestMapping("/uom")
public class UomController {
	
	@Autowired
	private IUomService service;
	
	@Autowired
	private UomUtil util;
	
	@Autowired
	private ServletContext sc;
	
	/**
	 * 1. show register page 
	 *    on enter  /register URL  (GET)
	 */
	@GetMapping("/register")
	public String showRegister(Model model) {
		//Form backing object//must written before return statement
		model.addAttribute("uom", new Uom());
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
		//Form backing object
		model.addAttribute("uom", new Uom());
		return "UomRegister";
	}
	/**
	 * 3. This method gets data from database using service 
	 * 	  and send to UI using model. It is called when 
	 *    Path: /all with type GET is requested.
	 * @param model is used to send data from controller to UI.
	 * @return the page i.e. UomData
	 */
	@GetMapping("/all")
	public String showAllUoms(
			Model model,
			@PageableDefault(page = 0, size = 3) Pageable pageable			
			
			) {
		
		//call to Service layer method
		//old code (before the implementation of pagination concept)
		/*
		List<Uom> list = service.getAllUoms();
		model.addAttribute("list", list);
		*/
		
		//new code
		//call service layer method
		Page<Uom> page = service.getAllUoms(pageable);
		//getContent() returns List<T> data exist in current page
		model.addAttribute("list", page.getContent());
		//to pass the pagination information 
		model.addAttribute("page", page);
		return "UomData";		
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
	public String deleteUom(
			@RequestParam Integer id,
			Model model
			) {
		//call service layer method
		service.deleteUom(id);
		//creating message to send to UI after the deletion of a record
		String message = new StringBuffer().append("Uom '")
				.append(id).append("' Deleted").toString();
		//send details to UI
		model.addAttribute("message", message);
		//model.addAttribute("list", service.getAllUoms());
		List<Uom> list = service.getAllUoms();
		model.addAttribute("list", list);
		return "UomData";		
	}
	/**
	 * 5. This method we have taken here to show Edit page
	 *    onclick of EDIT hyperlink.
	 *    ex- Req: /uom/edit?id=10   ,  Type: GET
	 *    Read object from database using PK(Id) and send it
	 *    to Edit form(UI)
	 *	  @return to the UomEdit page.
	 */
	@GetMapping("/edit")
	public String showEdit(
			@RequestParam Integer id,
			Model model
			) {
		//call service layer method
		Uom u = service.getOneUom(id);
		//send data to UI
		model.addAttribute("uom", u);
		return "UomEdit";
	}
	/**
	 * 6. On click of update button, read form data as 
	 *    ModelAttribute uom , send it to database
	 *    using Service layer. Redirect to all.
	 *    [Response.sendRedirect("/all")
	 * @return UomData page.
	 */
	@PostMapping("/update")
	public String updateUom(
			@ModelAttribute Uom uom,
			Model model
			) {
		//calling of service layer method
		service.updateUom(uom);
		//send details to UI
		model.addAttribute("message", "Uom with '"+uom.getId()+"' Updated" );
		model.addAttribute("list", service.getAllUoms());
		return "UomData";
	}
	/**
	 * 7. On click of Excel Export button, then control comes to
	 * 	  this method.
	 * @return ModelAndView object which in result export the data
	 * to Excel and download it in browser.
	 */
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m = new ModelAndView();		
		m.setView(new UomExcelView());
		
		//fetch data from database
		List list = service.getAllUoms();
		//send data to View class
		m.addObject("list", list);
		return m;
	}
	/**
	 * 8.
	 * @return
	 */
	@GetMapping("/pdf")
	public ModelAndView exportToPdf() {
		ModelAndView m = new ModelAndView();
		m.setView(new UomPdfView());
		
		//fetch data from the data base
		List<Uom> list = service.getAllUoms();
		//send data to View class
		m.addObject("list", list);
		return m;		
	}
	/**
	 * 9. AJAX VALIDATION Done in UI pages that's why this method is called 
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
	 * @param model, holds the value it receives from UI page via AJAX call.
	 * @return the message i.e. message.
	 */
	@GetMapping("/validate")
	public @ResponseBody String validateModel(@RequestParam String model,@RequestParam Integer id) {
		
		String message = "";
		
		////if id not exist then request came from Register page
		if(id == 0 && service.isUomModelExist(model)) { 
			message = new StringBuffer().append("Uom Model '").append(model)
					.append("' already exist").toString();
			
		} 
		//another if block
		//if id exist then request came from Edit page
		if (id != 0 && service.isUomModelExistForEdit(model, id)) {
			message = new StringBuffer().append("Uom Model '").append(model)
					.append("' already exist").toString();
		}
		return message;
	}
	/**
	 * 10. GENERATION OF PIE AND BAR CHARTS
	 * 	  This method is for the purpose of generartion of
	 * 	  pie chart and bar chart.
	 * @return
	 */
	@GetMapping("/charts")
	public String showCharts() {
		//call to a service layer method
		List<Object[]> data = service.getUomTypeAndCount();
		//getting the actual runtime path
		String path = sc.getRealPath("/");
		util.generatePieChart(path, data);//call to Util layer method
		util.generateBarChart(path, data);
		return "UomCharts";
	}

}
