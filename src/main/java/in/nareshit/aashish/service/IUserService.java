package in.nareshit.aashish.service;

import java.util.List;

import in.nareshit.aashish.model.User;

public interface IUserService {
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Integer saveUser(User user);
	/**
	 * 
	 * @return
	 */
	public List<User> getAllUsers();
	/**
	 * 
	 * @param id
	 * @param active
	 */
	public void modifyStatus(Integer id, boolean active);

}
