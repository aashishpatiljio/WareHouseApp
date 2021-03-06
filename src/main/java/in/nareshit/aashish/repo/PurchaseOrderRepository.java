package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	 
	/**
	 * 
	 * @param orderId
	 * @param status
	 */
	@Modifying
	@Query("UPDATE PurchaseOrder SET status=:status WHERE id=:orderId")
	public void updatePurchaseOrderStatusById(Integer orderId, String status);
	/**
	 * This method will fetch id, orderCode from PurchaseOrder followed by
	 * @param status
	 * @return List<Object[]>
	 */
	@Query("SELECT id,orderCode FROM PurchaseOrder WHERE status=:status")
	public List<Object[]> getPurchaseOrderIdAndCodeByStatus(String status);
}
