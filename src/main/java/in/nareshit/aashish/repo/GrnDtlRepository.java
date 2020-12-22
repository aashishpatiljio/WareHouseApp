package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.GrnDtl;

public interface GrnDtlRepository extends JpaRepository<GrnDtl, Integer> {
	
	/**
	 * This method will fetch the GRN Details by @param grnId
	 * @return List<GrnDtl>
	 */
	@Query("SELECT dtl FROM GrnDtl dtl JOIN dtl.grn as grn WHERE grn.id=:grnId")
	public List<GrnDtl> getAllGrnDtlsByGrnId(Integer grnId);

}
