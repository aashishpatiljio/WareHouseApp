package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.model.SaleOrder;
import in.nareshit.aashish.repo.SaleOrderRepository;
import in.nareshit.aashish.service.ISaleOrderService;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService {

	@Autowired
	private SaleOrderRepository repo;

	@Override
	public Integer saveSaleOrder(SaleOrder saleOrder) {
		return repo.save(saleOrder).getId(); 
	}

	@Override
	public List<SaleOrder> getAllSaleOrder() {
		return repo.findAll();
	}

}
