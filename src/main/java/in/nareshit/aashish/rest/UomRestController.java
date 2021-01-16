package in.nareshit.aashish.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nareshit.aashish.exception.UomNotFoundException;
import in.nareshit.aashish.model.Uom;
import in.nareshit.aashish.service.IUomService;
import in.nareshit.aashish.util.UomUtil;

@RestController
@RequestMapping("/rest/uom")
public class UomRestController {
	
	private Logger log = LoggerFactory.getLogger(UomRestController.class);
	
	@Autowired
	private IUomService service;
	@Autowired
	private UomUtil uomUtil;
	
	//1. Fetch all rows
	@GetMapping("/all")
	public ResponseEntity<?> fetchAllUoms(@PageableDefault(page = 0, size = 3) Pageable p){
		
		log.info("ENTERED INTO METHOD");
		
		ResponseEntity<?> resp = null;
		try {
			//without pagination, then below line
			//List<Uom> list = service.getAllUoms();
			Page<Uom> page = service.getAllUoms(p);
			log.info("SERVICE METHOD CALLED FOR ALL DATA FETCH");
			resp = new ResponseEntity<Page<Uom>>(page, HttpStatus.OK);
			log.info("SUCCESS RESPONSE CREATED");
		} catch (Exception e) {
			log.error("PROBLEM IN FETCHING DATA {}", e.getMessage());
			resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		log.info("ABOUT TO RETURN FROM METHOD");
		return resp;
	}
	//2. Insert Data/object
	/**
	 * @RequestBody is used here to fetch the JSON data from the Http request
	 * (Body Section), this fetched data is converted into Java object format
	 * and given as method input/param i.e. @param uom
	 * @return
	 */
	@PostMapping("/save")
	public ResponseEntity<String> saveUom(
			@RequestBody Uom uom
			){
		
		log.info("ENTERED INTO METHOD");

		ResponseEntity<String> resp = null;
		try {
			log.info("SERVICE METHOD IS ABOUT TO CALL FOR SAVE OPERATION");
			Integer id = service.saveUom(uom);
			log.debug("SERVICE METHOD IS CALLED FOR SAVE OPERATION AND SAVED WITH ID {}",id);

			String message = new StringBuffer().append("Uom with '")
					.append(id).append("' is saved").toString();
			resp = new ResponseEntity<String>(message, HttpStatus.CREATED);
			log.info("SUCCESS RESPONSE CONSTRUCTED");
		} catch (Exception e) {
			log.error("UNABLE TO SAVE UOM",e.getMessage());
			
			String message = "Unable to save Uom";
			resp = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}	
		log.info("ABOUT TO RETURN FROM METHOD");
		return resp;
	}
	//3. Fetch one row by id
	@GetMapping("/one/{id}")
	public ResponseEntity<?> fetchUomById(
			@PathVariable Integer id
			){ 
		ResponseEntity<?> resp = null;
		try {
			Uom uom = service.getOneUom(id);
			//resp = new ResponseEntity<Uom>(uom, HttpStatus.OK);
			resp = ResponseEntity.ok(uom);
		} catch (UomNotFoundException unfe) {
			throw unfe;		
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to fetch Uom", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	//4. Delete one row by id
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeUomById(
			@PathVariable Integer id
			){
		ResponseEntity<String> resp = null;
		try {
			service.deleteUom(id);
			String message = new StringBuffer().append("Uom with id '")
					.append(id).append("' deleted successfully").toString();
			resp = new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (UomNotFoundException unfe) {
			throw unfe;
		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityViolationException("Cannot delete Uom with id '"+id+"' ,may be it is to be used by one of the module");
		} catch (Exception e) {
			String message = new StringBuffer().append("Unable to delete Uom with id= '")
					.append(id).append("' ").toString();
			resp = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	//5. update data
	@PutMapping("/modify/{id}")
	public ResponseEntity<String> updateUomById(
			@PathVariable Integer id,
			@RequestBody Uom uom
			){
		
		ResponseEntity<String> resp = null;
		
		try {
			Uom dbUom = service.getOneUom(id);
			//this will copy the client sent uom obj data into dbUom obj
			uomUtil.copyNonNullValues(dbUom, uom);
			//call service layer method to update the record
			service.updateUom(dbUom);
			
			String message = new StringBuffer().append("Uom with id '")
					.append(id).append("' is updated").toString();
			resp = new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (UomNotFoundException unfe) {
			throw unfe;
		
		}catch (Exception e) {
			
			String message = new StringBuffer().append("Uom with id '")
					.append(id).append("' not updated").toString();
			resp = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
			
			e.printStackTrace();
		}
		return resp;
	}
	

}









