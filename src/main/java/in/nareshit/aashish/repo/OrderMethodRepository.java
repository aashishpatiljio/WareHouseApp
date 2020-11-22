package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer> {
	
	/**
	 * @param mode we have taken here to pass it to the named parameter in
	 * the Query, we are passing @param mode because we have two modes in
	 * OrderMethod UI register screen i.e. Sale and Purchase
	 * @return List<Object[]>
	 */
	@Query("SELECT id,orderCode FROM OrderMethod WHERE orderMode=:mode")
	public List<Object[]> getOrderMethodIdAndCodeByMode(String mode);
}
