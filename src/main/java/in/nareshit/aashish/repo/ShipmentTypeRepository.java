package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.ShipmentType;

public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Integer> {
	
	/**
	 * 1. This method is taken here for execution when Ajax call
	 * @param shipmentCode value comes from UI to controller to service to repository
	 * @return Integer value for count
	 */
	@Query("SELECT COUNT(shipmentCode) FROM ShipmentType WHERE shipmentCode=:shipmentCode")
	Integer getShipmentCodeCount(String shipmentCode);
	
	@Query("SELECT shipmentMode, COUNT(shipmentMode) FROM ShipmentType GROUP BY shipmentMode")
	List<Object[]> getShipmentModeAndCount();
}
