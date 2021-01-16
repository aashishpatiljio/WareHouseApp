package in.nareshit.aashish.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.nareshit.aashish.exception.PartNotFoundException;
import in.nareshit.aashish.exception.UomNotFoundException;
import in.nareshit.aashish.model.ErrorInfo;

/**
 * This class will handle the exceptions thrown from the Rest Controller class.
 * This class is adviced by Rest Controller class to handle the Exceptions.
 * The annotation @RestControllerAdvice makes this class to get  adviced by Rest 
 * Controller class.
 * @author aashish
 *
 */
@RestControllerAdvice
public class AppErrorHandler {
	
	/**
	 * Code written in the annotation @ExceptionHandler will get matched with
	 * the thrown exception. If it gets matched then the on the top of the method
	 * on which the annotation @ExceptionHandler is written that method will be 
	 * executed, handle the exception and give the response back to the browser.
	 * @param unfe reads the thrown exception
	 * @return RespnseEntity object .
	 */ 
	/*
	@ExceptionHandler(UomNotFoundException.class)
	public ResponseEntity<String> handleUomNotFoundEntity(UomNotFoundException unfe){
		
		return new ResponseEntity<String>(unfe.getMessage(), HttpStatus.NOT_FOUND);
	}
	*/
	/**
	 * This method will handle the same exception like the above method is handling.
	 * But this time, this method will show exception details in the JSON format, 
	 * whereas the above method shows the exception detail in the String format
	 */
	@ExceptionHandler(UomNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleUomNotFoundEntity(UomNotFoundException unfe){
		
		return new ResponseEntity<ErrorInfo>(new ErrorInfo(new Date().toString(), "NOT FOUND", 404, unfe.getMessage(), "UOM MODULE"), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PartNotFoundException.class)
	public ResponseEntity<String> handlePartNotFoundEntity(PartNotFoundException pnfe){
		
		return new ResponseEntity<String>(pnfe.getMessage(), HttpStatus.NOT_FOUND);
	}
	

}
