package in.nareshit.aashish.service;

import java.util.List;

import in.nareshit.aashish.model.User;

public interface IUserService {
	
	public Integer saveUser(User user);
	public List<User> getAllUsers();
	

}
