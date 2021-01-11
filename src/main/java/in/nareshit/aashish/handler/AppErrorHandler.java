package in.nareshit.aashish.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.nareshit.aashish.exception.UomNotFoundException;

@RestControllerAdvice
public class AppErrorHandler {
	
	@ExceptionHandler(UomNotFoundException.class)
	public ResponseEntity<String> handleUomNotFoundEntity(UomNotFoundException unfe){
		
		return new ResponseEntity<String>(unfe.getMessage(), HttpStatus.NOT_FOUND);
	}
	

}
