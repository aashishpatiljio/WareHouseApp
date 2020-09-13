package in.nareshit.aashish.service;

import in.nareshit.aashish.model.ShipmentType;

public interface IShipmentTypeService {
	/**
	 * This method reads form data as model class
	 * @param shipType indicates ModelAttribute
	 * @return Integer as PK after save
	 */
	public Integer saveShipmentType(ShipmentType shipType);

}
