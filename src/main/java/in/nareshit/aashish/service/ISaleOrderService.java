package in.nareshit.aashish.service;

import java.util.List;

import in.nareshit.aashish.model.SaleDtl;
import in.nareshit.aashish.model.SaleOrder;

public interface ISaleOrderService {
	/**
	 * This method reads form data as Model class object.
	 * @param saleOrder indicates ModelAttribute.
	 * @return Integer as Primary Key after save operation.
	 */
	public Integer saveSaleOrder(SaleOrder saleOrder);

	/**
	 * This method fetch the entire table data in the List<T> format.
	 * @return List<T>
	 */
	public List<SaleOrder> getAllSaleOrders();
	/**
	 * This method will fetch the one record/row from the database
	 * on the basis of @param id is passed from the controller and
	 * @return the data in the form of SaleOrder object.
	 */
	public SaleOrder getOneSaleOrder(Integer id);
	
	// METHODS for PO Screen#2
	/**
	 * This method reads form data as Model class object.
	 * @param saleDtl indicates ModelAttribute.
	 * @return Integer as Primary Key after save operation.
	 */
	public Integer saveSaleDtl(SaleDtl saleDtl);
	/**
	 * This method will fetch the details or records from the database
	 * related to SaleDtl on the basis of SO orderId.
	 * @param orderId reads Integer value of orderId from controller
	 * @return List<SaleDtl>
	 */
	public List<SaleDtl> getSaleDtlsByOrderId(Integer orderId);
	/**
	 * This method will delete one row/record of Sale Order Detail
	 * @param dtlId is reading the dtl id from Controller.
	 */
	public void removeSaleDtl(Integer dtlId);
}
