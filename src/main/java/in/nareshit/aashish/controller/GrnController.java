package in.nareshit.aashish.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.aashish.constant.OrderStatus;
import in.nareshit.aashish.model.Grn;
import in.nareshit.aashish.service.IGrnService;
import in.nareshit.aashish.service.IPurchaseOrderService;

@Controller
@RequestMapping("/grn")
public class GrnController {
	
	@Autowired
	private IGrnService service;
	
	@Autowired
	private IPurchaseOrderService poService;
	
	//define one private method so we can re-use it.
	//call this method where we need drop-down
	//call this private method for Register, Edit pages and save operation also.
	private void addDynamicUiComponents(Model model) {
		Map<Integer, String> map = poService.getPurchaseOrderIdAndCodeByStatus("INVOICED");
		model.addAttribute("pos", map);
	}
	
	/**
	 * 1. show Grn register page
	 * @return "GrnRegister" page
	 */
	@GetMapping("/register")
	public String showGrnRegisterpage(Model model) {
		//form backing object
		model.addAttribute("grn", new Grn());
		//call this method when we need dropdown(for integration)
		addDynamicUiComponents(model);
		return "GrnRegister";
	}
	/**
	 * 2. Save the Grn to Database
	 * @param grn reads the model attribute
	 * @param model will send data from controller to Ui
	 * @return "GrnRegister" page
	 */
	@PostMapping("/save")
	public String saveGrn(
			@ModelAttribute Grn grn,
			Model model
			) {
		//call service layer method to save the record to the database
		Integer id = service.saveGrn(grn);
		String message = new StringBuffer().append("Grn with id '")
				.append(id).append("' is saved").toString();
		
		model.addAttribute("message", message);
		//form backing object
		model.addAttribute("grn", new Grn());
		
		//On GRN save successful, PurchaseOrder status must be changed
		//grn.getPo().getId() = orderId
		poService.updateStatus(grn.getPo().getId(), OrderStatus.RECEIVED.name());
		
		//call this method when we need dropdown(for integration)
		addDynamicUiComponents(model);
		return "Grnregister";
	}

}
