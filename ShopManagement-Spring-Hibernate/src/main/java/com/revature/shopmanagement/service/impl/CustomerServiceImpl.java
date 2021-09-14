package com.revature.shopmanagement.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.shopmanagement.dao.CustomerDAO;
import com.revature.shopmanagement.model.Customer;
import com.revature.shopmanagement.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public String addCustomer(Customer customer) {
		return customerDAO.addCustomer(customer);
	}

	@Override
	public String updateCustomer(Customer customer) {
		return customerDAO.updateCustomer(customer);
	}

	@Override
	public String deleteCustomerById(Long custId) {
		return customerDAO.deleteCustomerById(custId);
	}

	@Override
	public Customer getCustomerById(Long custId) {
		return customerDAO.getCustomerById(custId);
	}

	@Override
	public Customer getCustomerByName(String custName) {
		return customerDAO.getCustomerByName(custName);
	}

	@Override
	public Customer getCustomerByPhoneNo(String custPhone) {
		return customerDAO.getCustomerByPhoneNo(custPhone);
	}

	@Override
	public boolean isCustomerExists(Long custId) {
		Customer customer = customerDAO.getCustomerById(custId);
		return (customer != null);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}

}
