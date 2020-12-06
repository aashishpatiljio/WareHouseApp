package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.exception.PurchaseOrderNotFoundException;
import in.nareshit.aashish.model.PurchaseOrder;
import in.nareshit.aashish.repo.PurchaseOrderRepository;
import in.nareshit.aashish.service.IPurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository repo;
	
	@Override
	public Integer savePurchaseOrder(PurchaseOrder po) {
		return repo.save(po).getId();
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return repo.findAll();
	}

	@Override
	public PurchaseOrder getOnePurchaseOrder(Integer id) {
		PurchaseOrder po = repo.findById(id).orElseThrow(
				()->new PurchaseOrderNotFoundException("Purchase Order '"+id+"' Not Exist")
				);
		return po;
	}

}
