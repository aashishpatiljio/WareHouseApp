package in.nareshit.aashish.service;

import java.util.List;

import in.nareshit.aashish.model.Part;

public interface IPartService {

	/**
	 * This method reads form data as model class
	 * 
	 * @param part indicates ModelAttribute
	 * @return Integer as PK after the save operation
	 */
	public Integer savePart(Part part);

	/**
	 * This method fetch the table data from database and save in the List format
	 * 
	 * @return List<T> after findAll() method operation
	 */
	public List<Part> getAllParts();

	/**
	 * This method takes one @param id (PK) and performs DELETE operations at DB.
	 */
	public void deletePart(Integer id);

	/**
	 * This method is used to read @param id as an input and 
	 * @return Part object else gives PartNotFoundException.
	 */
	public Part getOnePart(Integer id);

	/**
	 * This method is used to update Part based on id (PK)
	 * 
	 * @param part is taken as Model attribute
	 */
	public void updatePart(Part part);

}
