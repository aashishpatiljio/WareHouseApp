package in.nareshit.aashish.service;

import in.nareshit.aashish.model.OrderMethod;

public interface IOrderMethodService {
	/**
	 * This method reads form data as model class
	 * @param orderMethod indicates ModelAttribute
	 * @return Integer as PK after save
	 */
	public Integer saveOrderMethod(OrderMethod orderMethod);

}
