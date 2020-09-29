package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.exception.ShipmentTypeNotFoundException;
import in.nareshit.aashish.model.ShipmentType;
import in.nareshit.aashish.repo.ShipmentTypeRepository;
import in.nareshit.aashish.service.IShipmentTypeService;

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

	@Override
	public ShipmentType getOneShipmentType(Integer id) {
		ShipmentType shipType = repo.findById(id).orElseThrow(
				()-> new ShipmentTypeNotFoundException("ShipmentType '"+id+"' Not Exist")
				);
		return shipType;
	}

	@Override
	public void updateShipmentType(ShipmentType shipmentType) {
		repo.save(shipmentType);		
	}


}
