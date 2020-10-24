package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.exception.UomNotFoundException;
import in.nareshit.aashish.model.Uom;
import in.nareshit.aashish.model.Uom;
import in.nareshit.aashish.repo.UomRepository;
import in.nareshit.aashish.service.IUomService;

@Service
public class UomServiceImpl implements IUomService {

	@Autowired
	private UomRepository repo;

	@Override
	public Integer saveUom(Uom uom) {
		/*
		 * save(obj) method returns same object with id effected after save.
		 */
		return repo.save(uom).getId();
	}

	/**
	 * This method fetch the entire table data in List format
	 */
	@Override
	public List<Uom> getAllUoms() {
		return repo.findAll();
	}
	/**
	 * This method delete the row from the database table
	 * on the basis of id (PK) provided
	 */
	@Override
	public void deleteUom(Integer id) {
		//repo.deleteById(id);  //or
		//or, Uom u = getOneUom(id);
		//or
		Uom u = repo.findById(id).orElseThrow(
				()-> new UomNotFoundException("Uom '"+id+"' Not Exist")
				);
		repo.delete(u);
	}
	
	/**
	 * 
	 */
	@Override
	public Uom getOneUom(Integer id) {
		Uom u = repo.findById(id).orElseThrow(
				()-> new UomNotFoundException("Uom '"+id+"' Not Exist")
				);
		return u;
	}
	/**
	 * 
	 */
	@Override
	public void updateUom(Uom uom) {
		repo.save(uom);		
	}
	/**
	 * 
	 */
	@Override
	public boolean isUomModelExist(String uomModel) {
		boolean flag = false;  //initialize default value
		
		Integer count = repo.getUomModelCount(uomModel);
		if(count==0) {
			flag = false; // column value not exist
		}else {
			flag = true; // column value exist in db
		} 
		return flag;
		/*
		 * In real-time the above if-else logic is written in a short
		 * format like below-
		 * 
		 * return repo.getUomModelCount(uomModel)==0 ? false : true; or
		 * return repo.getUomModelCount(uomModel)>0 ? true : false;
		 */
	}
	@Override
	public List<Object[]> getUomTypeAndCount() {
		return repo.getUomTypeAndCount();
	}

}
