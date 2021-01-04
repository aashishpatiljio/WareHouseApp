package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.PurchaseDtl;

public interface PurchaseDtlRepository extends JpaRepository<PurchaseDtl, Integer> {
	
	/**
	 * This method will fetch the list of Purchase details where foreign
	 * key(orderId) is same 
	 * @param orderId reads foreign key from service layer.
	 * @return data in the form of List<PurchaseDtl>
	 */
	@Query("SELECT dtl FROM PurchaseDtl dtl JOIN  dtl.order as order WHERE order.id=:orderId")
	public List<PurchaseDtl> getPurchaseDtlsByOrderId(Integer orderId);
	/**
	 * This method will execute the given query and query returns an Integer count
	 * of dtlId followed by @param orderId.
	 */
	@Query("SELECT count(dtl.id) FROM PurchaseDtl dtl JOIN  dtl.order as order WHERE order.id=:orderId")
	public Integer getPurchaseDtlsCountByOrderId(Integer orderId);

}
 