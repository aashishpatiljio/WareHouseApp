package in.nareshit.aashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.ShipmentType;

public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Integer> {
	
	@Query("SELECT COUNT(shipmentCode) FROM ShipmentType WHERE shipmentCode=:shipmentCode")
	Integer getShipmentCodeCount(String shipmentCode);
}
