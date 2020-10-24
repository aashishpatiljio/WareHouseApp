package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer> {
	
	//(:uomModel--> named parameter is used)
	@Query("SELECT COUNT(uomModel) FROM Uom WHERE uomModel=:uomModel")
	Integer getUomModelCount(String uomModel);
	
	@Query("SELECT uomType, COUNT(uomType) from Uom GROUP BY uomType")
	List<Object[]> getUomTypeAndCount();

}
