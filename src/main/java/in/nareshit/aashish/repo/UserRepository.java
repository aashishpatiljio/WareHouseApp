package in.nareshit.aashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.aashish.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
