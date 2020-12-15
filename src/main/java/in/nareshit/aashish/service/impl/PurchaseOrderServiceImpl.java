package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.aashish.exception.PurchaseOrderNotFoundException;
import in.nareshit.aashish.model.PurchaseDtl;
import in.nareshit.aashish.model.PurchaseOrder;
import in.nareshit.aashish.repo.PurchaseDtlRepository;
import in.nareshit.aashish.repo.PurchaseOrderRepository;
import in.nareshit.aashish.service.IPurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository repo;
	@Autowired
	private PurchaseDtlRepository dtlRepo;
	
	/**
	 * 
	 */
	@Override
	public Integer savePurchaseOrder(PurchaseOrder po) {
		return repo.save(po).getId();
	}
	/**
	 * 
	 */
	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return repo.findAll();
	}
	/**
	 * 
	 */
	@Override
	public PurchaseOrder getOnePurchaseOrder(Integer id) {
		PurchaseOrder po = repo.findById(id).orElseThrow(
				()->new PurchaseOrderNotFoundException("Purchase Order '"+id+"' Not Exist")
				);
		return po;
	}
	
	//METHODS for SCREEN#2 of PURCHASE ORDER
	/**
	 * 
	 */
	@Override
	public Integer savePurchaseDtl(PurchaseDtl dtl) {
		return dtlRepo.save(dtl).getId();
	}
	/**
	 * This method will fetch the details of Purchase Details (in Screen#2)
	 * followed by order id using the JOIN query written in PurchaseDtlRepository
	 * interface, and it will fetch the data in the List<PurchaseDtl> format.
	 */
	@Override
	public List<PurchaseDtl> getPurchaseDtlsByOrderId(Integer orderId) {
		List<PurchaseDtl> list = dtlRepo.getPurchaseDtlsByOrderId(orderId);
		return list;
	}
	/**
	 * This method will delete one record from the purchase details table.
	 */
	@Override
	public void removePurchaseDtl(Integer dtlId) {
		dtlRepo.deleteById(dtlId);   //id is dtl id		
	}
	/**
	 * Here this method is updating the status based on orderId.
	 */
	@Override
	@Transactional
	public void updateStatus(Integer orderId, String status) {
		repo.updatePurchaseOrderStatusById(orderId, status);
	}
	/**
	 * 
	 */
	@Override
	public Integer getPurchaseDtlsCountByOrderId(Integer orderId) {
		return dtlRepo.getPurchaseDtlsCountByOrderId(orderId);		
	}

}
