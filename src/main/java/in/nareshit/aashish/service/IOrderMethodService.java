package in.nareshit.aashish.service;

import java.util.List;
import java.util.Map;

import in.nareshit.aashish.model.OrderMethod;

public interface IOrderMethodService {
	/**
	 * This method reads form data as model class
	 * @param orderMethod indicates ModelAttribute
	 * @return Integer as PK after save
	 */
	public Integer saveOrderMethod(OrderMethod orderMethod);
	/**
	 * This method fetch the table data from database
	 * and save in the List format
	 * @return List<T> after findAll() method operation.
	 */
	public List<OrderMethod> getAllOrderMethods();
	/**
	 * This method takes one @param id (PK) as an input and
	 * performs DELETE operations at DB.
	 */
	public void deleteOrderMethod(Integer id);
	/**
	 * This method is used to read @param id as an
	 * input and @return OrdertMethod object else
	 * OrderMethodNotFoundException
	 */
	public OrderMethod getOneOrderMethod(Integer id);
	/**
	 * This method is used to update OrderMethod based on id (PK)
	 * @param orderMethod is taken as Model attribute
	 */
	public void updateOrderMethod(OrderMethod orderMethod);
	/**
	 * This method will be used to fetch the data from database using method
	 * we declared in repository interface in the form of List<Object[]> type
	 * and then convert this List<Object[]> into Map<Integer,String>
	 * @param mode
	 * @return Map<Integer,String>
	 */
	public Map<Integer,String> getOrderMethodIdAndCodeByMode(String mode);
	

}
