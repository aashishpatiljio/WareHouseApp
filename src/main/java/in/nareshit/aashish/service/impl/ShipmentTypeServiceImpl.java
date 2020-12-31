package in.nareshit.aashish.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.exception.ShipmentTypeNotFoundException;
import in.nareshit.aashish.model.ShipmentType;
import in.nareshit.aashish.repo.ShipmentTypeRepository;
import in.nareshit.aashish.service.IShipmentTypeService;
import in.nareshit.aashish.util.MyCollectionUtil;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {

	@Autowired
	private ShipmentTypeRepository repo;
	
	@Override
	public Integer saveShipmentType(ShipmentType shipmentType) {
		/*
		 * save(obj) method returns same object with id effected
		 * after save.
		 */
		return repo.save(shipmentType).getId();
	}
	
	/**
	 * This method fetch the table data from the database
	 * in the List<T> format
	 */
	@Override
	public List<ShipmentType> getAllShipmentTypes() {
		return repo.findAll();
	}
	/**
	 * 
	 */
	@Override
	public void deleteShipmentType(Integer id) {
		//repo.deleteById(id);  //or
		//or, ShipmentType shipType = getOneShipmentType(id);
		//or
		ShipmentType shipType = repo.findById(id).orElseThrow(
				()-> new ShipmentTypeNotFoundException("ShipmentType '"+id+"' Not Exist")
				);
		repo.delete(shipType);
	}
	/**
	 * 
	 */
	@Override
	public ShipmentType getOneShipmentType(Integer id) {
		ShipmentType shipType = repo.findById(id).orElseThrow(
				()-> new ShipmentTypeNotFoundException("ShipmentType '"+id+"' Not Exist")
				);
		return shipType;
	}
	/**
	 * 
	 */
	@Override
	public void updateShipmentType(ShipmentType shipmentType) {
		repo.save(shipmentType);		
	}
	/**
	 * 
	 */
	@Override
	public boolean isShipmentCodeExist(String shipmentCode) {
		boolean flag = false;
		
		Integer count = repo.getShipmentCodeCount(shipmentCode);
		if (count==0) {
			flag = false;	// column value not exist		
		}else {
			flag = true;	// column value exist in db
		}
		return flag;
	}
	/**
	 * 
	 */
	@Override
	public List<Object[]> getShipmentModeAndCount() {
		return repo.getShipmentModeAndCount();		
	}
	/**
	 * It fetches the data from the database using Repository layer method
	 * in the form of List<Object[]> and convert it into Map<Integer,String>
	 * in the service layer.
	 */
	@Override
	public Map<Integer, String> getShipmentIdAndCodeByEnabled(String enabledShipment) {
		List<Object[]> list = repo.getShipmentIdAndCodeByEnabled(enabledShipment);
		Map<Integer, String> map = MyCollectionUtil.convertListToMap(list);
		return map;
	}
	/**
	 * @see in.nareshit.aashish.service.IShipmentTypeService
	 */
	@Override
	public Page<ShipmentType> getAllShipmentTypes(Pageable pageable) {
		Page<ShipmentType> page  = repo.findAll(pageable);
		return page;
	} 


}
