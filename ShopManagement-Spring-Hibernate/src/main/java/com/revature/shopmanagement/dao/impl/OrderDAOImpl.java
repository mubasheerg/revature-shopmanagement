package com.revature.shopmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.revature.shopmanagement.dao.OrderDAO;
import com.revature.shopmanagement.entity.Order;
import com.revature.shopmanagement.entity.Product;
import com.revature.shopmanagement.exception.DataBaseException;

@Repository
public class OrderDAOImpl implements OrderDAO {

	private static final Logger logger = LogManager.getLogger(CartDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_ALL_ORDERS = "select o from Order o";

	@Transactional
	@Override
	public String addOrder(Order order) {
		logger.info("add order called");
		try {
			Session session = sessionFactory.getCurrentSession();
			order.setOrderedOn(new Date());
			session.save(order);
			return "Order added successfully";
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Transactional
	@Override
	public String updateOrder(Order order) {
		logger.info("update order called");
		try {
			Session session = sessionFactory.getCurrentSession();
			order.setOrderedOn(new Date());
			session.merge(order);
			return "Order updated successfully!";
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Transactional
	@Override
	public String deleteOrderById(Long orderId) {
		logger.info("delete order called");
		try {
			Session session = sessionFactory.getCurrentSession();
			Order order = getOrderById(orderId);
			session.delete(order);
			return "Order deleted successfully";
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public Order getOrderById(Long orderId) {
		logger.info("getting order by id");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Order.class, orderId);
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public boolean isOrderExists(Long orderId) {
		logger.info("checking existance of order");
		Session session = sessionFactory.getCurrentSession();
		Order order = session.get(Order.class, orderId);
		return (order != null);
	}

	@Override
	public List<Order> getAllOrders() {
		logger.info("getting all orders");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Order> query = session.createQuery(GET_ALL_ORDERS, Order.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

}
