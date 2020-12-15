package in.nareshit.aashish.service;

import java.util.List;

import in.nareshit.aashish.model.PurchaseDtl;
import in.nareshit.aashish.model.PurchaseOrder;

public interface IPurchaseOrderService {
	
	// methods for PO Screen#1
	/**
	 * This method reads form data as Model class object.
	 * @param purchaseOrder indicates ModelAttribute.
	 * @return Integer as Primary Key after save operation.
	 */
	public Integer savePurchaseOrder(PurchaseOrder po);
	/**
	 * This method fetch the entire table data in the List<T> format.
	 * @return List<T>
	 */
	public List<PurchaseOrder> getAllPurchaseOrders();
	/**
	 * This method will fetch the one record/row from the database
	 * on the basis of @param id is passed from the controller and
	 * @return the data in the form of PurchaseOrder object.
	 */
	public PurchaseOrder getOnePurchaseOrder(Integer id);
	
	
	// METHODS for PO Screen#2
	/**
	 * This method reads form data as Model class object.
	 * @param dtl indicates ModelAttribute.
	 * @return Integer as Primary Key after save operation.
	 */
	public Integer savePurchaseDtl(PurchaseDtl dtl);
	/**
	 * This method will fetch the details or records from the database
	 * related to PurchaseDtls on the basis of PO id
	 * @param orderId reads Integer value of orderId from controller
	 * @return List<PurchaseDtl>
	 */
	public List<PurchaseDtl> getPurchaseDtlsByOrderId(Integer orderId);
	/**
	 * This method will delete one row/record of Purchase Detail
	 * @param id is reading the dtl id from Controller.
	 */
	public void removePurchaseDtl(Integer dtlId);
	/**
	 * 
	 * @param orderId
	 * @param status
	 */
	public void updateStatus(Integer orderId, String status);
	/**
	 * This method will fetch the total number of counts of
	 * purchase details.
	 * @param orderId reads purchase order id from controller
	 * @return Integer value.
	 */
	public Integer getPurchaseDtlsCountByOrderId(Integer orderId);
}
