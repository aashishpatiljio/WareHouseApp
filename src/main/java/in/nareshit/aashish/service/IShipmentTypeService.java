package in.nareshit.aashish.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	/**
	 * 
	 * @param id
	 */
	public void deleteShipmentType(Integer id);
	/**
	 * 
	 * @return
	 */
	public ShipmentType getOneShipmentType(Integer id);
	/**
	 * 
	 * @param shipType
	 */
	public void updateShipmentType(ShipmentType shipmentType);
	/**
	 *  
	 * @param shipmentCode
	 * @return
	 */
	public boolean isShipmentCodeExist(String shipmentCode);
	/**
	 * 
	 * @return
	 */
	public List<Object[]> getShipmentModeAndCount();
	/**
	 * 
	 * @param enabledShipment
	 * @return
	 */
	public Map<Integer, String> getShipmentIdAndCodeByEnabled(String enabledShipment);
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	public Page<ShipmentType> getAllShipmentTypes(Pageable pageable);

}
