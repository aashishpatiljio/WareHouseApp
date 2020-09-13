package in.nareshit.aashish.service;

import java.util.List;

import in.nareshit.aashish.model.ShipmentType;

public interface IShipmentTypeService {
	/**
	 * This method reads form data as model class
	 * @param shipmentType indicates ModelAttribute
	 * @return Integer as PK after save
	 */
	public Integer saveShipmentType(ShipmentType shipmentType);
	
	/**
	 * This method fetch the table data from database
	 * and save in the List format
	 * @return List<T> after findAll() method operation
	 */
	public List<ShipmentType> getAllShipmentTypes();

}
