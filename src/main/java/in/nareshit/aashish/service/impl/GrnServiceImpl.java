package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.model.Grn;
import in.nareshit.aashish.repo.GrnRepository;
import in.nareshit.aashish.service.IGrnService;

@Service
public class GrnServiceImpl implements IGrnService {

	@Autowired
	private GrnRepository repo;
	
	/**
	 * 
	 */
	@Override
	public Integer saveGrn(Grn grn) {
		return repo.save(grn).getId();
	}
	/**
	 * 
	 */
	@Override
	public List<Grn> getAllGrns() {
		return repo.findAll();
	}

}
