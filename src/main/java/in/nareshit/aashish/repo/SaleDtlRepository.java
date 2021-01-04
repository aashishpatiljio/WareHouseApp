package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.SaleDtl;

public interface SaleDtlRepository extends JpaRepository<SaleDtl, Integer> {
	
	/**
	 * This method will fetch the list of Sale details where foreign
	 * key(orderId) is same 
	 * @param orderId reads foreign key from service layer.
	 * @return data in the form of List<SaleDtl>
	 */
	@Query("SELECT dtl from SaleDtl dtl JOIN dtl.order as order WHERE order.id=:orderId")
	public List<SaleDtl> getSaleDtlsByOrderId(Integer orderId);
	/**
	 * This method will execute the given query and query returns an Integer count
	 * of dtlId followed by @param orderId.
	 */
	@Query("SELECT COUNT(dtl.id) FROM SaleDtl dtl JOIN dtl.order as order WHERE order.id=:orderId")
	public Integer getSaleDtlsCountByOrderId(Integer orderId);

}
