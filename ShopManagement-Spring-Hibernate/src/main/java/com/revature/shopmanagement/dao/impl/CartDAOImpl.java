package com.revature.shopmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.revature.shopmanagement.dao.CartDAO;
import com.revature.shopmanagement.entity.Cart;
import com.revature.shopmanagement.exception.DataBaseException;

@Repository
public class CartDAOImpl implements CartDAO {

	private static final Logger logger = LogManager.getLogger(CartDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_ALL_CARTS = "select c from Cart c";

	@Transactional
	@Override
	public String addCart(Cart cart) {
		logger.info("add cart");
		try {
			Session session = sessionFactory.getCurrentSession();
			cart.setCartAddedOn(new Date());
			session.save(cart);
			return "Cart added successfully : " + localTime;
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}

	}

	@Transactional
	@Override
	public String updateCart(Cart cart) {
		logger.info("update cart");
		try {
			Session session = sessionFactory.getCurrentSession();
			cart.setCartUpdatedOn(new Date());
			session.merge(cart);
			return "Cart updated successfully!";
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Transactional
	@Override
	public String deleteCartById(Long cartId) {
		logger.info("delete cart by id");
		try {
			Session session = sessionFactory.getCurrentSession();
			Cart cart = getCartById(cartId);
			session.delete(cart);
			return "Cart deleted successfully";
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public Cart getCartById(Long cartId) {
		logger.info("getting cart by id");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Cart.class, cartId);
		} catch (Exception e) {
			throw new DataBaseException("Error in databse");
		}

	}

	@Override
	public boolean isCartExists(Long cartId) {
		logger.info("checking existance of cart");
		Session session = sessionFactory.getCurrentSession();
		Cart cart = session.get(Cart.class, cartId);
		return (cart != null);
	}

	@Override
	public List<Cart> getAllCartDetails() {
		logger.info("getting all cart");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Cart> query = session.createQuery(GET_ALL_CARTS, Cart.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

}
