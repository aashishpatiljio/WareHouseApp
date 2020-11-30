package in.nareshit.aashish.service;

import java.util.List;

import in.nareshit.aashish.model.PurchaseOrder;

public interface IPurchaseOrderService {
	
	/**
	 * This method reads form data as Model class object.
	 * @param purchaseOrder indicates ModelAttribute.
	 * @return Integer as Primary Key after save opration
	 */
	public Integer savePurchaseOrder(PurchaseOrder purchaseOrder);
	/**
	 * This method fetch the entire table data in the List<T> format.
	 * @return List<T>
	 */
	public List<PurchaseOrder> getAllPurchaseOrders();

}
