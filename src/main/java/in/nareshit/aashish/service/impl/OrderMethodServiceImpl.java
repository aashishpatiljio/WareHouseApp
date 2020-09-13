package in.nareshit.aashish.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
