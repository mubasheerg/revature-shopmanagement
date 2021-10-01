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
import com.revature.shopmanagement.dao.ProductDAO;
import com.revature.shopmanagement.entity.Product;
import com.revature.shopmanagement.exception.DataBaseException;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private static final Logger logger = LogManager.getLogger(CartDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_PRODUCT_BY_NAME = "select p from Product p where prodName=?1";
	private static final String GET_PRODUCT_BY_CATEGORY = "select p from Product p where category=?1";
	private static final String GET_ALL_PRODUCTS = "select p from Product p";

	@Transactional
	@Override
	public String addProduct(Product product) {
		logger.info("add product called");
		try {
			Session session = sessionFactory.getCurrentSession();
			product.setProdAddedOn(new Date());
			session.save(product);
			return "Product added successfully at : " + localTime;
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Transactional
	@Override
	public String updateProduct(Product product) {
		logger.info("update product called");
		try {
			Session session = sessionFactory.getCurrentSession();
			product.setProdUpdatedOn(new Date());
			session.merge(product);
			return "Product updated successfully!";
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}

	}

	@Transactional
	@Override
	public String deleteProductById(Long prodId) {
		logger.info("delete product called");
		try {
			Session session = sessionFactory.getCurrentSession();
			Product product = getProductById(prodId);
			session.delete(product);
			return "Product deleted successfully";
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public Product getProductById(Long prodId) {
		logger.info("getting product by id");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Product.class, prodId);
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public List<Product> getProductByName(String prodName) {
		logger.info("getting product by name");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Product> resultList = session.createQuery(GET_PRODUCT_BY_NAME, Product.class).setParameter(1, prodName)
					.getResultList();
			return (resultList.isEmpty() ? null : (List<Product>) resultList.get(0));
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public Product getProductByCategory(String category) {
		logger.info("getting product by category");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Product> resultList = session.createQuery(GET_PRODUCT_BY_CATEGORY, Product.class)
					.setParameter(1, category).getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public boolean isProductExists(Long prodId) {
		logger.info("checking existance of product");
		Session session = sessionFactory.getCurrentSession();
		Product product = session.get(Product.class, prodId);
		return (product != null);
	}

	@Override
	public List<Product> getAllProducts() {
		logger.info("getting all products");
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			Query<Product> query = session.createQuery(GET_ALL_PRODUCTS, Product.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}
}
