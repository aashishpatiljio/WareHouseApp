package in.nareshit.aashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	
	@Modifying
	@Query("UPDATE PurchaseOrder SET status=:status WHERE id=:orderId")
	public void updatePurchaseOrderStatusById(Integer orderId, String status);
}
