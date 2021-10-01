package com.revature.shopmanagement.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.shopmanagement.dao.OrderDAO;
import com.revature.shopmanagement.dto.OrderDTO;
import com.revature.shopmanagement.entity.Order;
import com.revature.shopmanagement.entity.Product;
import com.revature.shopmanagement.exception.DuplicateIdException;
import com.revature.shopmanagement.exception.IdNotFoundException;
import com.revature.shopmanagement.exception.NullValueException;
import com.revature.shopmanagement.mapper.OrderMapper;
import com.revature.shopmanagement.mapper.ProductMapper;
import com.revature.shopmanagement.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDAO orderDAO;

	@Override
	public String addOrder(OrderDTO orderDTO) {
		logger.info("add order");
		Order order = OrderMapper.dtoToEntity(orderDTO);
		Long orderId = order.getOrderId();
		if (orderDAO.isOrderExists(orderId)) {
			throw new DuplicateIdException("Order with Id :" + orderId + " already exists");
		}
		return orderDAO.addOrder(order);
	}

	@Override
	public String updateOrder(OrderDTO orderDTO) {
		logger.info("update order");
		Order order = OrderMapper.dtoToEntity(orderDTO);
		Long orderId = order.getOrderId();
		if (orderDAO.isOrderExists(orderId))
			return orderDAO.updateOrder(order);
		else
			throw new IdNotFoundException("Order with Id: " + orderId + " is not found");
	}

	@Override
	public String deleteOrderById(Long orderId) {
		logger.info("delete order by id");
		if (orderDAO.isOrderExists(orderId))
			return orderDAO.deleteOrderById(orderId);
		else
			throw new IdNotFoundException("order with Id: " + orderId + " is not found");
	}

	@Override
	public Order getOrderById(Long orderId) {
		logger.info("getting order by id");
		if (orderDAO.isOrderExists(orderId))
			return orderDAO.getOrderById(orderId);
		else
			throw new IdNotFoundException("order with Id: " + orderId + " is not found");
	}

	@Override
	public boolean isOrderExists(Long orderId) {
		logger.info("checking existance of order by id");
		return orderDAO.isOrderExists(orderId);
	}

	@Override
	public List<Order> getAllOrders() {
		logger.info("getting order by name");
		List<Order> orders = orderDAO.getAllOrders();
		if (CollectionUtils.isEmpty(orders))
			throw new NullValueException("No datas Found");
		return orders;

	}

}
