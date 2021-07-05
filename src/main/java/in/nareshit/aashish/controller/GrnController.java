package in.nareshit.aashish.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.nareshit.aashish.constant.OrderStatus;
import in.nareshit.aashish.model.Grn;
import in.nareshit.aashish.model.GrnDtl;
import in.nareshit.aashish.model.PurchaseDtl;
import in.nareshit.aashish.service.IGrnService;
import in.nareshit.aashish.service.IPurchaseOrderService;

@Controller
@RequestMapping("/grn")
public class GrnController {
	
	@Autowired
	private IGrnService service;
	
	@Autowired
	private IPurchaseOrderService poService;
	
	
	//define one private method so we can re-use it..
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
		
		createGrnDtls(grn);
		return "Grnregister";
	}
	
	//on saving of Grn,,,,,, GrnDtls is created
	private void createGrnDtls(Grn grn) {
		//a#. Read PurchaseOrder Id using Grn linked PO
		Integer orderId = grn.getPo().getId(); //Po Id
		//b#. Read all PurchaseDtls data using PurchaseOrder Id
		List<PurchaseDtl> poDtlsList = poService.getPurchaseDtlsByOrderId(orderId);
		
		//c#. Create one model class i.e. GrnDtl (it is already created)
		//here we have to set PurchaseDtl data to GrnDtl
		for (PurchaseDtl purchaseDtl : poDtlsList) {
			//d#. Create one GrnDtl object using PurchaseDtl object
			GrnDtl grnDtl = new GrnDtl();
			
			grnDtl.setItemCode(purchaseDtl.getPart().getPartCode());
			grnDtl.setBaseCost(purchaseDtl.getPart().getPartCost());
			grnDtl.setQty(purchaseDtl.getQuantity());
			grnDtl.setItemVal(grnDtl.getBaseCost() * grnDtl.getQty());
			
			//e#. Link GrnDtl object to Grn object
			grnDtl.setGrn(grn);
			
			//f#. save GrnDtl object
			service.saveGrnDtl(grnDtl);
		}
	}
	//----------------------------------------------------------------------------	
	/**
	 * 3. Show All Grns records
	 * @return "GrnData" page
	 */
	@GetMapping("/all")
	public String showAllGrns(Model model) {
		List<Grn> list = service.getAllGrns();
		model.addAttribute("list", list);
		return "GrnData";
	}
	
	/**
	 * 4. show GrnDtlsView (Screen#2) based on GrnId 
	 * @param grnId reads Grn id from Ui
	 * @param model is used to send the data from controller to Ui
	 * @return "GrnDtlsView" page
	 */
	@GetMapping("/viewParts")
	public String showGrnDtls(
			@RequestParam Integer grnId,
			Model model
			) {
		//Grn Object
		Grn grn = service.getOneGrnById(grnId);
		model.addAttribute("grn", grn);
		//Grn Dtls
		List<GrnDtl> list = service.getAllGrnDtlsByGrnId(grnId);
		model.addAttribute("list", list);  //sending data to the Ui
		
		return "GrnDtlsView";
	}
	/**
	 * 5. Update GrnDtl Status.
	 * 
	 * @param grnId reads Grn Id from UI
	 * @param grnDtlId reads GrnDtl Id from UI
	 * @param grnDtlStatus reads GrnDtl status from UI
	 * @return "GrnDtlsView" page
	 */
	@GetMapping("/updateStatus")
	public String updateGrnDtlStatus(
			@RequestParam Integer grnId,
			@RequestParam Integer grnDtlId,
			@RequestParam String grnDtlStatus
			) {
		service.updateGrnDtlStatus(grnDtlId, grnDtlStatus);
		return "redirect:viewParts?grnId="+grnId;
	}
	
}
