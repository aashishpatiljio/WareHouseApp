package in.nareshit.aashish.service;

import java.util.List;

import in.nareshit.aashish.model.Grn;

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
	

}
