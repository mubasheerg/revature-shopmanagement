package com.revature.shopmanagement.service;

import java.util.List;

import com.revature.shopmanagement.dto.OrderDTO;
import com.revature.shopmanagement.entity.Order;

public interface OrderService {
	public String addOrder(OrderDTO orderDTO);

	public String updateOrder(OrderDTO orderDTO);

	public String deleteOrderById(Long orderId);

	public Order getOrderById(Long orderId);

	public boolean isOrderExists(Long orderId);

	public List<Order> getAllOrders();
}
