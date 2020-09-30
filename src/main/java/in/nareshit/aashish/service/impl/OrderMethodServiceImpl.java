package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.exception.OrderMethodNotFoundException;
import in.nareshit.aashish.model.OrderMethod;
import in.nareshit.aashish.repo.OrderMethodRepository;
import in.nareshit.aashish.service.IOrderMethodService;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	private OrderMethodRepository repo;
	
	@Override
	public Integer saveOrderMethod(OrderMethod orderMethod) {
		/*
		 * save(obj) method returns same object with id effected
		 * after save.
		 */
		return repo.save(orderMethod).getId();
	}

	@Override
	public List<OrderMethod> getAllOrderMethods() {
		return repo.findAll();
	}

	@Override
	public void deleteOrderMethod(Integer id) {
		//repo.deleteById(id);  //or
		//or, OrderMethod orderMethod = getOneOrderMethod(id);
		//or
		OrderMethod orderMethod = repo.findById(id).orElseThrow(
				()-> new OrderMethodNotFoundException("OrderMethod '"+id+"' Not Exist")
				);	
		repo.delete(orderMethod);
	}

	@Override
	public OrderMethod getOneOrderMethod(Integer id) {
		OrderMethod orderMethod = repo.findById(id).orElseThrow(
				()-> new OrderMethodNotFoundException("OrderMethod '"+id+"' Not Exist")
				);
		return orderMethod;
	}

	@Override
	public void updateOrderMethod(OrderMethod orderMethod) {
		repo.save(orderMethod);		
	}

}
