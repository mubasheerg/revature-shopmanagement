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

import com.revature.shopmanagement.controller.MailSend;
import com.revature.shopmanagement.dao.CustomerDAO;
import com.revature.shopmanagement.entity.Customer;
import com.revature.shopmanagement.exception.DataBaseException;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private static final Logger logger = LogManager.getLogger(CustomerDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_CUSTOMER_BY_NAME = "select c from Customer c where c.custName=?1";
	private static final String GET_CUSTOMER_BY_PHONENO = "select c from Customer c where c.custPhone=?1";
	private static final String GET_CUSTOMER_BY_MAIL = "select c from Customer c where c.custMail=?1";
	private static final String GET_ALL_CUSTOMERS = "select c from Customer c";
	private static final String CUSTOMER_LOGIN = "select c from Customer u where custMail=?1 and c.custPwd=?2";

	@Transactional
	@Override
	public String addCustomer(Customer customer) {
		logger.info("add customer called");
		try {
			Session session = sessionFactory.getCurrentSession();
			customer.setCustCreatedOn(new Date());
			session.save(customer);
			return "Customer successfully created at : " + localTime;
		} catch (Exception e) {
			throw new DataBaseException("Error in Database");
		}

	}

	@Transactional
	@Override
	public String updateCustomer(Customer customer) {
		logger.info("update customer called");
		try {
			Session session = sessionFactory.getCurrentSession();
			customer.setCustUpdatedOn(new Date());
			session.merge(customer);
			return "Customer updated successfully!";
		} catch (Exception e) {
			throw new DataBaseException("Error in Database");
		}
	}

	@Transactional
	@Override
	public String deleteCustomerById(Long custId) {
		logger.info("delete customer called");
		try {
			Session session = sessionFactory.getCurrentSession();
			Customer customer = getCustomerById(custId);
			session.delete(customer);
			return "Customer deleted successfully";
		} catch (Exception e) {
			throw new DataBaseException("Error in Database");
		}
	}

	@Override
	public Customer getCustomerById(Long custId) {
		logger.info("getting customer by id");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Customer.class, custId);
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public List<Customer> getCustomerByName(String custName) {
		logger.info("getting customer by name");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Customer> resultList = session.createQuery(GET_CUSTOMER_BY_NAME, Customer.class)
					.setParameter(1, custName).getResultList();
			return (resultList.isEmpty() ? null : (List<Customer>) resultList.get(0));
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public Customer getCustomerByMail(String custMail) {
		logger.info("getting customer by mail");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Customer> resultList = session.createQuery(GET_CUSTOMER_BY_MAIL, Customer.class)
					.setParameter(1, custMail).getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public Customer getCustomerByPhoneNo(String custPhone) {
		logger.info("getting customer by phoneNo");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Customer> resultList = session.createQuery(GET_CUSTOMER_BY_PHONENO, Customer.class)
					.setParameter(1, custPhone).getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public boolean isCustomerExists(Long custId) {
		logger.info("checking existance of customer");
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, custId);
		return (customer != null);
	}

	@Override
	public List<Customer> getAllCustomers() {
		logger.info("all customer info called");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Customer> query = session.createQuery(GET_ALL_CUSTOMERS, Customer.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public Customer customerLogin(String custMail, String custPwd) {
		logger.info("User login");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Customer> resultList = session.createQuery(CUSTOMER_LOGIN, Customer.class).setParameter(1, custMail)
					.setParameter(2, custPwd).getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			throw new DataBaseException("Error in DataBase");
		}
	}

	@Transactional
	@Override
	public String forgotPassword(String custMail, String custPwd) {
		logger.info("Forgot password");
		try {
			Session session = sessionFactory.getCurrentSession();
			Customer customer = getCustomerByMail(custMail);
			customer.setCustPwd(custPwd);
			session.update(customer);
			MailSend.sendMail(customer.getCustMail(), "New Password", "Hi, " + customer.getCustName()
					+ "\nThe new password has been generated!!!" + "\n New Password :" + customer.getCustPwd());
			return "Password generated and updated succesfully!";
		} catch (Exception e) {
			throw new DataBaseException("Error in updating data in DataBase");
		}
	}
}
