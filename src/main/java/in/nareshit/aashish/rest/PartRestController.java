package in.nareshit.aashish.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nareshit.aashish.model.Part;
import in.nareshit.aashish.service.IPartService;

@RestController
@RequestMapping("/rest/part")
public class PartRestController {
	
	private Logger log = LoggerFactory.getLogger(PartRestController.class);

	@Autowired
	private IPartService service;
	
	//1. save Part data
	public ResponseEntity<String> savePart(
			@RequestBody Part part
			){
		log.info("ENTERED INTO SAVE METHOD"); 
		ResponseEntity<String> resp = null;
		try {
		Integer id = service.savePart(part);
		String message = new StringBuffer().append("Part with id '")
				.append(id).append("' is saved").toString();
		log.info(message);
		resp = new ResponseEntity<String>(message, HttpStatus.OK);
		} catch(Exception e) {
			log.error("Unable to create Part : {}", e.getMessage());
			String message = "Unable to save Part";
			resp = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		log.info("ABOUT TO EXIT FROM SAVE METHOD");
		return resp;
	}
}
