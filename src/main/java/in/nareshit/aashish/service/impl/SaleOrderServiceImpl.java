package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.aashish.exception.SaleOrderNotFoundException;
import in.nareshit.aashish.model.SaleDtl;
import in.nareshit.aashish.model.SaleOrder;
import in.nareshit.aashish.repo.SaleDtlRepository;
import in.nareshit.aashish.repo.SaleOrderRepository;
import in.nareshit.aashish.service.ISaleOrderService;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService {

	@Autowired
	private SaleOrderRepository repo;
	@Autowired
	private SaleDtlRepository dtlRepo;
	
	/**
	 * @see in.nareshit.aashish.service.ISaleOrderService
	 */
	@Override
	public Integer saveSaleOrder(SaleOrder saleOrder) {
		return repo.save(saleOrder).getId(); 
	}
	/**
	 * @see in.nareshit.aashish.service.ISaleOrderService
	 */
	@Override
	public List<SaleOrder> getAllSaleOrders() {
		return repo.findAll();
	}
	/**
	 * @see in.nareshit.aashish.service.ISaleOrderService
	 */
	@Override
	public SaleOrder getOneSaleOrder(Integer id) {
		SaleOrder so = repo.findById(id).orElseThrow(
				()-> new SaleOrderNotFoundException("SaleOrder with '"+id+"' not exist")
				);
		return so;
	}
	/**
	 * @see in.nareshit.aashish.service.ISaleOrderService
	 */
	@Override
	public Integer saveSaleDtl(SaleDtl saleDtl) {
		return dtlRepo.save(saleDtl).getId();
	}
	/**
	 * @see in.nareshit.aashish.service.ISaleOrderService
	 */
	@Override
	public List<SaleDtl> getSaleDtlsByOrderId(Integer orderId) {
		List<SaleDtl> list = dtlRepo.getSaleDtlsByOrderId(orderId);
		return list;
	}
	/**
	 * @see in.nareshit.aashish.service.ISaleOrderService
	 */
	@Override
	public void removeSaleDtl(Integer dtlId) {
		dtlRepo.deleteById(dtlId);  //id is dtl id
	}
	/**
	 * @see in.nareshit.aashish.service.ISaleOrderService
	 */
	@Override
	@Transactional
	public void updateStatus(Integer orderId, String status) {
		repo.updateSaleOrderStatusById(orderId, status);		
	}
	/**
	 * @see in.nareshit.aashish.service.ISaleOrderService
	 */
	@Override
	public Integer getSaleDtlsCountByOrderId(Integer orderId) {
		return dtlRepo.getSaleDtlsCountByOrderId(orderId);
	}

}
