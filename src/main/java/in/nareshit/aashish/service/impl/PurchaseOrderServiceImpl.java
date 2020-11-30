package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import in.nareshit.aashish.model.PurchaseOrder;
import in.nareshit.aashish.repo.PurchaseOrderRepository;
import in.nareshit.aashish.service.IPurchaseOrderService;

public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository repo;
	
	@Override
	public Integer savePurchaseOrder(PurchaseOrder purchaseOrder) {
		return repo.save(purchaseOrder).getId();
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return repo.findAll();
	}

}
