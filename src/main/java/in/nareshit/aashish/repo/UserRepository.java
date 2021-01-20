package in.nareshit.aashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.aashish.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Modifying
	@Query("UPDATE in.nareshit.aashish.model.User SET active=:active WHERE id=:id")
	public void updateStatus(Integer id, boolean active);

}
