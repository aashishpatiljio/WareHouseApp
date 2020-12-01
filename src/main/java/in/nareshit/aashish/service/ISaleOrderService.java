package in.nareshit.aashish.service;

import java.util.List;

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
	public List<SaleOrder> getAllSaleOrder();
}
