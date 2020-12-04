package in.nareshit.aashish.service;

import java.util.List;
import java.util.Map;

import in.nareshit.aashish.model.WhUserType;

public interface IWhUserTypeService {
	
	/**
	 * 
	 * @param whUserType
	 * @return
	 */
	public Integer saveWhUserType(WhUserType whUserType);
	/**
	 * 
	 * @return
	 */
	public List<WhUserType> getAllWhUserTypes();
	/**
	 * 
	 * @param id
	 */
	public void deleteWhUserType(Integer id);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public WhUserType getOneWhUserType(Integer id);
	/**
	 * 
	 * @param whUserType
	 */
	public void updateWhUserType(WhUserType whUserType);
	/**
	 * 
	 * @param mail
	 * @return
	 */
	public boolean isWhUserMailIdExist(String mail);
	/**
	 * This method gets id and code on the bais of userType value passed
	 * from Controller.
	 * It gets the fetched data in the Map<Integer,String> format
	 * @param userType reads the value from the controller.
	 * @return Map<Integer, String>
	 */
	public Map<Integer, String> getWhUserIdAndCodeByType(String userType);
}
