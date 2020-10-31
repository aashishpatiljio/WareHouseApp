package in.nareshit.aashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.WhUserType;

public interface WhUserTypeRepository extends JpaRepository<WhUserType, Integer> {

	@Query("SELECT COUNT(userEmail) FROM WhUserType WHERE userEmail=:userEmail")
	Integer getWhUserEmailCount(String userEmail);
}
