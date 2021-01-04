package in.nareshit.aashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.SaleOrder;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer> {
	
	@Modifying
	@Query("UPDATE SaleOrder SET status=:status WHERE id=:orderId")
	public void updateSaleOrderStatusById(Integer orderId, String status);

}
