package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.aashish.model.User;
import in.nareshit.aashish.repo.UserRepository;
import in.nareshit.aashish.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public Integer saveUser(User user) {
		Integer id = repo.save(user).getId();
		return id;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = repo.findAll();
		return list;
	}

	@Override
	@Transactional
	public void modifyStatus(Integer id, boolean active) {
		repo.updateStatus(id, active);		
	}
	
	

}
