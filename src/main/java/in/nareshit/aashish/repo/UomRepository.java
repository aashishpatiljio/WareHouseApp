package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer> {
	
	//This method is for Uom Register page for not for Edit page also.
	//this method is for Ajax call
	//(:uomModel--> named parameter is used)
	@Query("SELECT COUNT(uomModel) FROM Uom WHERE uomModel=:uomModel")
	Integer getUomModelCount(String uomModel);
	
	/**
	 * This method is for AJAX Call. It will count the given uomModel value
	 * by comparing with all other records present in the database table except
	 * current row(because it is edit operation, not new record created) that's
	 * why we are writing id!=:id in the below query.
	 * @param uomModel reads value form the Ui->Controller->Service->Repository
	 * @param id
	 * @return Integer count
	 */
	@Query("SELECT COUNT(uomModel) FROM Uom WHERE uomModel=:uomModel and id!=:id")
	Integer getUomModelCountForNotId(String uomModel, Integer id);
	
	/**
	 * 
	 * @return
	 */
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
