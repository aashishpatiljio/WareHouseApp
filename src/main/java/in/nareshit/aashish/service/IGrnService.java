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
	 * 
	 * @param grnDtl
	 * @return
	 */
	public Integer saveGrnDtl(GrnDtl grnDtl);

}
