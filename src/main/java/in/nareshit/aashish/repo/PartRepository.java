package in.nareshit.aashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.aashish.model.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {

}
