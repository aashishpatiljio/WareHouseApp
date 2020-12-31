package in.nareshit.aashish.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.exception.UomNotFoundException;
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
	/**
	 * @see in.nareshit.aashish.service.IUomService 
	 */
	@Override
	public boolean isUomModelExistForEdit(String uomModel, Integer id) {
		return repo.getUomModelCountForNotId(uomModel, id) > 0 ? true : false;
		//return repo.getUomModelCountForNotId(uomModel, id) > 0;
	}
	/**
	 * @see in.nareshit.aashish.service.IUomService
	 */
	@Override
	public List<Object[]> getUomTypeAndCount() {
		return repo.getUomTypeAndCount();
	}
	/**
	 * This method fetch UOM ID and UOM Model in Map format.
	 * @see in.nareshit.aashish.service.IUomService
	 */
	@Override
	public Map<Integer, String> getUomIdAndModel() {
		List<Object[]> list = repo.getUomIdAndModel();
		
		//LinkedHashMap preserves the insertion order
		Map<Integer, String> map = new LinkedHashMap<>();
		//to convert List<Object[]> list into Map format
		for(Object[] ob:list) {
			map.put(Integer.valueOf(ob[0].toString()), ob[1].toString());
		}
		
		//alternative logic for above commented code  
		/*
		Map<Integer, String> map =
				list.stream()
				.collect(
						Collectors.toMap(ob->Integer.valueOf(ob.toString()), ob->ob.toString()));
		*/ 
		return map;
	}
	
	/**
	 * @see in.nareshit.aashish.service.IUomService
	 */
	@Override
	public Page<Uom> getAllUoms(Pageable pageable) {
		Page<Uom> page = repo.findAll(pageable);
		return page;
		
		//return repo.findAll(pageable);
	}
	/**
	 * 
	 */
	@Override
	public Page<Uom> findByUomModelContaining(String uomModel, Pageable pageable) {
		Page<Uom> page = repo.findByUomModelContaining(uomModel, pageable);
		return page;
	}


}
