package com.revature.shopmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.revature.shopmanagement.dao.CustomerDAO;
import com.revature.shopmanagement.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * Session session = null; private Session getSession() { return
	 * sessionFactory.getCurrentSession(); }
	 * 
	 * Transaction transaction = null; private Transaction getTransaction() { return
	 * session.beginTransaction(); }
	 */
	@Override
	public String addCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		Long custId = null;
		try {

			Transaction transaction = session.beginTransaction();
			session.save(customer);
			transaction.commit();
			custId = customer.getCustId();
		} catch (HibernateException e1) {
			e1.printStackTrace();
		}

		return (custId != null) ? "Customer successfully created at : " + localTime
				: "Customer is not created...Try again";
	}

	@Override
	public String updateCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.merge(customer);
			transaction.commit();
		} catch (HibernateException e1) {
			e1.printStackTrace();
		}
		return "Customer updated successfully!";
	}

	@Override
	public String deleteCustomerById(Long custId) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = getCustomerById(custId);
		try {
			Transaction transaction = session.beginTransaction();
			session.delete(customer);
			transaction.commit();
		} catch (HibernateException e1) {
			e1.printStackTrace();
		}
		return (customer != null) ? "Customer successfully deleted at : " + localTime : "Customer is not deleted!";
	}

	@Override
	public Customer getCustomerById(Long custId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Customer.class, custId);
	}

	@Override
	public Customer getCustomerByName(String custName) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Customer> resultList = session.createQuery("select c from Customer c where c.custName=?1")
				.setParameter(1, custName).getResultList();
		return (resultList.isEmpty() ? null : resultList.get(0));
	}

	@Override
	public Customer getCustomerByPhoneNo(String custPhone) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Customer> resultList = session.createQuery("select c from Customer c where c.custPhone=?1")
				.setParameter(1, custPhone).getResultList();
		return (resultList.isEmpty() ? null : resultList.get(0));
	}

	@Override
	public boolean isCustomerExists(Long custId) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, custId);
		return (customer != null);
	}

	@Override
	public List<Customer> getAllCustomers() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Customer> query = session.createQuery("select c from Customer c");
		return (query.getResultList().isEmpty() ? null : query.getResultList());
	}

}
