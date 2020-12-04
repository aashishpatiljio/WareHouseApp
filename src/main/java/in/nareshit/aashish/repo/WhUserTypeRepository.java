package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.WhUserType;

public interface WhUserTypeRepository extends JpaRepository<WhUserType, Integer> {
	/**
	 * This method gives the count of the Email Id, if count is greater 
	 * than 0 then EmailID exist in database.
	 * @param userEmail reads the input Email ID from the service.
	 * @return a Integer value(counts).
	 */
	@Query("SELECT COUNT(userEmail) FROM WhUserType WHERE userEmail=:userEmail")
	Integer getWhUserEmailCount(String userEmail);

	/**
	 * This method will fetch id, userCode on the basis of value passed in
	 * the @param userType. 
	 * @param userType reads the value from service layer
	 * @return the fetched data in the form of List<Object>.
	 */
	@Query("SELECT id,userCode FROM WhUserType WHERE userType=:userType")
	List<Object[]> getWhUserIdAndCodeByType(String userType);
}
