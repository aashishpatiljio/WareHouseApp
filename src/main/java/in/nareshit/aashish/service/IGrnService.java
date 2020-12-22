package in.nareshit.aashish.service;

import java.util.List;

import in.nareshit.aashish.model.Grn;
import in.nareshit.aashish.model.GrnDtl;

public interface IGrnService {
	
	/**
	 * 
	 * @param grn
	 * @return
	 */
	public Integer saveGrn(Grn grn);
	/**
	 * 
	 * @return
	 */
	public List<Grn> getAllGrns();
	/**
	 * This method will fetch one record followed by id
	 * @param id reads id value from Controller
	 * @return Grn object
	 */
	public Grn getOneGrnById(Integer grnId); 
	/**
	 * 
	 * @param grnDtl
	 * @return
	 */
	public Integer saveGrnDtl(GrnDtl grnDtl);
	
	//for Screen#2	
	/**
	 * This method will fetch all Grn Details followed by @param grnId
	 */
	public List<GrnDtl> getAllGrnDtlsByGrnId(Integer grnId);

}
