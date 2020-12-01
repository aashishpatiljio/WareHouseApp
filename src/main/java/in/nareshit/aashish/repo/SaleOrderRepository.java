package in.nareshit.aashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.aashish.model.SaleOrder;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer> {

}
