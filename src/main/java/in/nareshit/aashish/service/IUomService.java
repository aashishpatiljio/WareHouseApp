package in.nareshit.aashish.service;

import java.util.List;

import in.nareshit.aashish.model.Uom;

public interface IUomService {
	/**
	 * This method reads form data as model class
	 * @param uom indicates ModelAttribute
	 * @return Integer as PK after the save operation
	 */
	public Integer saveUom(Uom uom);
	/**
	 * This method fetch the table data from database
	 * and save in the List format
	 * @return List<T> after findAll() method operation
	 */
	public List<Uom> getAllUoms();

}
