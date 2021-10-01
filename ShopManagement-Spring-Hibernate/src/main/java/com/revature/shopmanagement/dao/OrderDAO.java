package com.revature.shopmanagement.dao;

import java.util.List;
import com.revature.shopmanagement.entity.Order;

public interface OrderDAO {
	public String addOrder(Order order);

	public String updateOrder(Order order);

	public String deleteOrderById(Long orderId);

	public Order getOrderById(Long orderId);
	
	public boolean isOrderExists(Long orderId);

	public List<Order> getAllOrders();
}
