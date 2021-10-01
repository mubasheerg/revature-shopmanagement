package com.revature.shopmanagement.mapper;

import com.revature.shopmanagement.dto.OrderDTO;
import com.revature.shopmanagement.entity.Order;

public class OrderMapper {
	public static Order dtoToEntity(OrderDTO orderDTO) {
		Order order = new Order();
		order.setOrderId(orderDTO.getOrderId());
		order.setAmount(orderDTO.getAmount());
		order.setOrderedOn(orderDTO.getOrderAddedOn());

		return order;
	}
}
