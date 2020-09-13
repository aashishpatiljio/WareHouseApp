package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		 * save(obj) method returns same object with id effected
		 * after save.
		 */
		return repo.save(uom).getId();
	}

	@Override
	public List<Uom> getAllUoms() {
 		return repo.findAll();
	}

}
