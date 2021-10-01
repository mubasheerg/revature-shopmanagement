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
import com.revature.shopmanagement.dao.StocksDAO;
import com.revature.shopmanagement.entity.Cart;
import com.revature.shopmanagement.entity.Stocks;
import com.revature.shopmanagement.exception.DataBaseException;

@Repository
public class StocksDAOImpl implements StocksDAO {

	private static final Logger logger = LogManager.getLogger(CartDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_ALL_STOCKS = "select s from Stocks s";

	@Transactional
	@Override
	public String addStocks(Stocks stocks) {
		logger.info("add stocks");
		try {
			Session session = sessionFactory.getCurrentSession();
			stocks.setStockAddedOn(new Date());
			session.save(stocks);
			return "Stock added successfully at : " + localTime;
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}

	}

	@Transactional
	@Override
	public String updateStocks(Stocks stocks) {
		logger.info("update stocks");
		try {
			Session session = sessionFactory.getCurrentSession();
			stocks.getStockUpdatedOn();
			session.merge(stocks);
			return "Stock updated successfully!";
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Transactional
	@Override
	public String deleteStocksById(Long stockId) {
		logger.info("add stocks");
		try {
			Session session = sessionFactory.getCurrentSession();
			Stocks stocks = getStocksById(stockId);
			session.delete(stocks);
			return "Stocks deleted succesfully";
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}

	}

	@Override
	public Stocks getStocksById(Long stockId) {
		logger.info("getting stocks by id");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Stocks.class, stockId);
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public boolean isStockExists(Long stockId) {
		logger.info("getting stocks by id");
		Session session = sessionFactory.getCurrentSession();
		Stocks stocks = session.get(Stocks.class, stockId);
		return (stocks != null);
	}

	@Override
	public List<Stocks> getAllStocks() {
		logger.info("getting all stocks");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Stocks> query = session.createQuery(GET_ALL_STOCKS, Stocks.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

}
