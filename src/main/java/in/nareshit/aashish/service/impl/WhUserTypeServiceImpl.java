package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.exception.WhUserTypeNotFoundException;
import in.nareshit.aashish.model.WhUserType;
import in.nareshit.aashish.repo.WhUserTypeRepository;
import in.nareshit.aashish.service.IWhUserTypeService;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService {

	@Autowired
	private WhUserTypeRepository repo;

	/**
	 * 
	 */
	@Override
	public Integer saveWhUserType(WhUserType whUserType) {
		return repo.save(whUserType).getId();
	}
	/**
	 * 
	 */
	@Override
	public List<WhUserType> getAllWhUserTypes() {
		return repo.findAll();
	}
	/**
	 * 
	 */
	@Override
	public void deleteWhUserType(Integer id) {
		WhUserType whUserType = repo.findById(id).orElseThrow(
				()-> new WhUserTypeNotFoundException("WhUserType with '"+id+"' not exist")
				);
		repo.delete(whUserType);
	}
	/**
	 * 
	 */
	@Override
	public WhUserType getOneWhUserType(Integer id) {
		WhUserType whUserType = repo.findById(id).orElseThrow(
				()-> new WhUserTypeNotFoundException("WhUserType with '"+id+"' not exist")
				);
		return whUserType;
	}
	/**
	 * 
	 */
	@Override
	public void updateWhUserType(WhUserType whUserType) {
		repo.save(whUserType);
	}

}
