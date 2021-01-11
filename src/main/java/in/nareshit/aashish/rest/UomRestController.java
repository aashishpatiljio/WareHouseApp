package in.nareshit.aashish.rest;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private IUomService service;
	@Autowired
	private UomUtil uomUtil;
	
	//1. Fetch all rows
	@GetMapping("/all")
	public ResponseEntity<?> fetchAllUoms(@PageableDefault(page = 0, size = 3) Pageable p){
		ResponseEntity<?> entity = null;
		try {
			//without pagination, then below line
			//List<Uom> list = service.getAllUoms();
			Page<Uom> page = service.getAllUoms(p);
			entity = new ResponseEntity<Page<Uom>>(page, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
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
		
		ResponseEntity<String> resp = null;
		try {
			Integer id = service.saveUom(uom);
			String message = new StringBuffer().append("Uom with '")
					.append(id).append("' is saved").toString();
			resp = new ResponseEntity<String>(message, HttpStatus.CREATED);
		} catch (Exception e) {
			String message = "Unable to save Uom";
			resp = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}		
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
			//this will copy the uom obj data into dbUom obj
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









