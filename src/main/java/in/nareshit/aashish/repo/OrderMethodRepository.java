package in.nareshit.aashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.aashish.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer> {

}
