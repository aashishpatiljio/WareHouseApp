package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.GrnDtl;

public interface GrnDtlRepository extends JpaRepository<GrnDtl, Integer> {
	
	/**
	 * This method will fetch the GRN Details by @param grnId
	 * @return List<GrnDtl>
	 */
	@Query("SELECT dtl FROM GrnDtl dtl JOIN dtl.grn as grn WHERE grn.id=:grnId")
	public List<GrnDtl> getAllGrnDtlsByGrnId(Integer grnId);
	/**
	 * To update the GrnDtl status by grnDtlId.
	 * We have to apply an annotation i.e. @Modifying because by-default
	 * @Query behaves like a select query so when we apply @Modifying then
	 * it will behaves like a non-select query, and also we have apply one 
	 * more annotation here i.e. @Transactional but better to write in on the  
	 * top of the method which will call this current method in the service layer.
	 * 
	 * @param grnDtlId reads the GrnDtl id from service layer.
	 * @param grnDtlStatus reads the GrnDtl status from service layer
	 * @return an Integer value.
	 */
	@Modifying
	@Query("UPDATE GrnDtl SET status=:grnDtlStatus WHERE id=:grnDtlId")
	public Integer updateGrnDtlStatus(Integer grnDtlId, String grnDtlStatus);

}
 