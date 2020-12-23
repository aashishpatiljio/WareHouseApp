package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.aashish.model.Grn;
import in.nareshit.aashish.model.GrnDtl;
import in.nareshit.aashish.repo.GrnDtlRepository;
import in.nareshit.aashish.repo.GrnRepository;
import in.nareshit.aashish.service.IGrnService;

@Service
public class GrnServiceImpl implements IGrnService {

	@Autowired
	private GrnRepository repo;
	@Autowired
	private GrnDtlRepository grnDtlRepo;
	
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
	/**
	 * 
	 */
	@Override
	public Integer saveGrnDtl(GrnDtl grnDtl) {
		return grnDtlRepo.save(grnDtl).getId();
	}
	/** 
	 * 
	 */
	@Override
	public List<GrnDtl> getAllGrnDtlsByGrnId(Integer grnId) {
		return grnDtlRepo.getAllGrnDtlsByGrnId(grnId);
	}
	/**
	 * 
	 */
	@Override
	public Grn getOneGrnById(Integer grnId) {
		return repo.findById(grnId).get();
	}
	/**
	 * 
	 */
	@Transactional
	@Override
	public Integer updateGrnDtlStatus(Integer grnDtlId, String grnDtlStatus) {
		return grnDtlRepo.updateGrnDtlStatus(grnDtlId, grnDtlStatus);		
	}

}
