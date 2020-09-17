package in.nareshit.aashish.exception;

public class ShipmentTypeNotFoundException extends RuntimeException{
	
	public ShipmentTypeNotFoundException() {
		super();
	}
	
	public ShipmentTypeNotFoundException(String message) {
		super(message);
	}
}