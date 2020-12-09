package in.nareshit.aashish.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {
	
	/**
	 * This method is used here to fetch id, partCode from database
	 * and it @return List<Object[]> format
	 */
	@Query("SELECT p.id, p.partCode FROM Part p")
	public List<Object[]> getPartIdandCode();
}
