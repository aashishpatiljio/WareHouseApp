package in.nareshit.aashish.service;

import java.util.List;

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
}
