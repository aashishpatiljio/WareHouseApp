package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer> {

	//this method is for Ajax call
	//(:uomModel--> named parameter is used)
	@Query("SELECT COUNT(uomModel) FROM Uom WHERE uomModel=:uomModel")
	Integer getUomModelCount(String uomModel);

	@Query("SELECT uomType, COUNT(uomType) from Uom GROUP BY uomType")
	List<Object[]> getUomTypeAndCount();
	
	/**
	 * This method we have taken here for integration of Uom module in Part
	 * module.
	 * This method fetches the data according to the query given.
	 * @return List<Object[]>
	 */
	@Query("SELECT id,uomModel FROM Uom")
	List<Object[]> getUomIdAndModel();

}
