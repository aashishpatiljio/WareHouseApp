package in.nareshit.aashish.service;

import java.util.List;
import java.util.Map;

import in.nareshit.aashish.model.Uom;

public interface IUomService {
	/**
	 * This method reads form data as model class
	 * 
	 * @param uom indicates ModelAttribute
	 * @return Integer as PK after the save operation
	 */
	public Integer saveUom(Uom uom);

	/**
	 * This method fetch the table data from database and save in the List format
	 * 
	 * @return List<T> after findAll() method operation
	 */
	public List<Uom> getAllUoms();

	/**
	 * This method takes one @param id (PK) and performs DELETE operations at DB.
	 */
	public void deleteUom(Integer id);

	/**
	 * This method is used to read @param id as an input and @return Uom object else
	 * UomNotFoundException
	 */
	public Uom getOneUom(Integer id);

	/**
	 * This method is used to update Uom based on id (PK)
	 * 
	 * @param uom is taken as Model attribute
	 */
	public void updateUom(Uom uom);

	/**
	 * 
	 * @param uomModel
	 * @return
	 */
	public boolean isUomModelExist(String uomModel);
	/**
	 * 
	 * @return
	 */
	public List<Object[]> getUomTypeAndCount(); 
	/**
	 * This method is declared for integration of module purpose.
	 * Here we are trying to fetch id and model from database and
	 * store in the Object[] format and then convert it into
	 * Map<Integer,String> format
	 * @return
	 */
	public Map<Integer, String> getUomIdAndModel();

}
