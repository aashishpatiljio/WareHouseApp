package in.nareshit.aashish.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.model.ShipmentType;
import in.nareshit.aashish.repo.ShipmentTypeRepository;
import in.nareshit.aashish.service.IShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {

	@Autowired
	private ShipmentTypeRepository repo;
	
	@Override
	public Integer saveShipmentType(ShipmentType shipType) {
		/*
		 * save(obj) method returns same object with id effected
		 * after save.
		 */
		return repo.save(shipType).getId();
	}

}
