package in.nareshit.aashish.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import in.nareshit.aashish.model.ShipmentType;
import in.nareshit.aashish.service.IShipmentTypeService;
import in.nareshit.aashish.util.ShipmentTypeUtil;
import in.nareshit.aashish.view.ShipmentTypeExcelView;
import in.nareshit.aashish.view.ShipmentTypePdfView;

@Controller
@RequestMapping("/shipmenttype")
public class ShipmentTypeController {
	
	@Autowired
	private IShipmentTypeService service;
	
	@Autowired
	private ShipmentTypeUtil util;
	
	@Autowired
	private ServletContext context;
	
	/**
	 * 1. show register page 
	 *    on enter  /register URL  (GET)
	 */
	@GetMapping("/register")
	public String showRegister(Model model) {
		//form backing object
		model.addAttribute("shipmentType", new ShipmentType());
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
		String message = new StringBuffer().append("ShipmentType with '")
				.append(id).append("' Saved").toString();
		//send data to UI
		model.addAttribute("message", message);
		//form backing object
		model.addAttribute("shipmentType", new ShipmentType());
		return "ShipmentTypeRegister";		
	}
	/**
	 * 3. This method gets data from database using service layer
	 *    and send to UI using Model memory. It is called when
	 *    Path: /all with type GET is requested. 
	 * @param model is used to send data from controller to UI
	 * @return the page i.e. ShipmentTypeData
	 */
	@GetMapping("/all")
	public String showAllShipmentTypes(Model model) {
		List<ShipmentType> list = service.getAllShipmentTypes();
		model.addAttribute("list", list);
		return "ShipmentTypeData";
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
	 * @return the same page i.e. ShipmentTypeData
	 */
	@GetMapping("/delete")
	public String deleteShipmentType(
			@RequestParam Integer id,
			Model model
			) {
		//call service layer method
		service.deleteShipmentType(id);
		//creating a message to send to UI after deletion of record
		String message = new StringBuffer().append("ShipmentType with '")
				      .append(id).append("' Deleted").toString();
		//sending data to UI
		model.addAttribute("message", message);
		model.addAttribute("list", service.getAllShipmentTypes());
		return "ShipmentTypeData";
	}
	/**
	 * 5. This method we have taken here to show Edit page
	 * 	  onclick of EDIT hyperlink.
	 *    ex- Req: /uom/edit?id=10   ,  Type: GET
	 *    Read object from database using PK(Id) and send it
	 *    to Edit form(UI) using @param model.
	 * @param id is used to read id value dynamically onclick of
	 * Edit button
	 * @param model is used to send data from controller to UI
	 * @return to the ShipmentTypeEdit page.
	 */
	@GetMapping("/edit")
	public String showEdit(
			@RequestParam Integer id,
			Model model
			) {
		//call service layer method
		ShipmentType shipType = service.getOneShipmentType(id);
		//send data to UI
		model.addAttribute("shipment", shipType);
		return "ShipmentTypeEdit";		
	}
	/**
	 * 6. On click of update button, read form data as 
	 *    ModelAttribute shipmentType , send it to database
	 *    using Service layer. Redirect to all.
	 *    [Response.sendRedirect("/all")
	 * @return ShipmentTypeData.
	 */
	@PostMapping("/update")
	public String updateShipmentType(
			@ModelAttribute ShipmentType shipmentType,
			Model model
			) {
		//call service layer method
		service.updateShipmentType(shipmentType);
		//send details to UI
		model.addAttribute("message", "ShipmentType with '"+shipmentType.getId()+"' Updated");
		model.addAttribute("list", service.getAllShipmentTypes());
		return "ShipmentTypeData";
	}
	/**
	 * 7. On click of Excel Export button, control comes to this method and
	 *    @return ModelAndView object which is responsible for downloading the
	 *    Excel file
	 */
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m = new ModelAndView();
		
		
		//fetching the data from the database by service call
		List<ShipmentType> list = service.getAllShipmentTypes();		
		
		if(list==null || list.isEmpty()) {  //if data not exist
			m.addObject("message", "NO DATA FOR EXCEL EXPORT");
			m.setViewName("ShipmentTypeData");
			
		}else {  //if data exist then export
			//set View class object to the ModelAndView obj
			m.setView(new ShipmentTypeExcelView());
			//send the data to the View class
			m.addObject("list", list);
		}
		return m;
	}
	/**
	 * 8. On click of PDF EXPORT button, the control comes to this method
	 *    and it @return ModelAndView object which is responsible for 
	 *    downloading the Pdf 
	 */
	@GetMapping("/pdf")
	public ModelAndView exportToPdf() {
		
        ModelAndView m = new ModelAndView();
		
		//fetching the data from the database by service call
		List<ShipmentType> list = service.getAllShipmentTypes();		
		
		if(list==null || list.isEmpty()) {  //if data not exist
			m.addObject("message", "NO DATA FOR PDF EXPORT");
			m.setViewName("ShipmentTypeData");
			
		}else {  //if data exist then export
			//set View class object to the ModelAndView obj
			m.setView(new ShipmentTypePdfView());
			//send the data to the View class
			m.addObject("list", list);
		}
		return m;
	}
	/**
	 *  9. AJAX VALIDATION
	 * @param shipmentCode
	 * @return
	 * 
	 * If we don't write @ResponseBody then it will expect
	 * return type as a page name but this time we want to 
	 * return the data/message to show it on browser.
	 */
	@GetMapping("/validate")
	public @ResponseBody String validateShipmentCode(@RequestParam String code) {
		String message = "";
		
		if(service.isShipmentCodeExist(code)) {
			message = new StringBuffer().append("Shipment Code '").append(code)
					.append("' already exist").toString();
		}
		return message;
	}
	/**
	 * 10. JFREE CHARTS GENERATION
	 * 
	 * The purpose of this method is to generate the Pie Charts
	 * and Bar chart on request.
	 * @return the page where Charts will be shown.
	 */
	@GetMapping("/charts")
	public String generateCharts() {
		//call to service layer method to get data
		List<Object[]> data = service.getShipmentModeAndCount();
		//call to utility class method
		String path = context.getRealPath("/");
		util.generatePieChart(path, data);
		util.generateBarChart(path, data);
		return "ShipmentTypeCharts";		
	}

}
