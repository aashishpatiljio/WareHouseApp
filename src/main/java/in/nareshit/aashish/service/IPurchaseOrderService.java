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
	
	// methods for PO Screen#1
	/**
	 * This method reads form data as Model class object.
	 * @param dtl indicates ModelAttribute.
	 * @return Integer as Primary Key after save operation.
	 */
	public Integer savePurchaseDtl(PurchaseDtl dtl);
	

}
