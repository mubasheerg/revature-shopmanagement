package com.revature.shopmanagement.service.impl;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.shopmanagement.controller.MailSend;
import com.revature.shopmanagement.dao.CustomerDAO;
import com.revature.shopmanagement.dto.CustomerDTO;
import com.revature.shopmanagement.entity.Customer;
import com.revature.shopmanagement.exception.*;
import com.revature.shopmanagement.mapper.CustomerMapper;
import com.revature.shopmanagement.service.CustomerService;
import com.revature.shopmanagement.util.PasswordGenerator;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public String addCustomer(CustomerDTO customerDTO) {
		logger.info("add customer");
		Customer customer = CustomerMapper.dtoToEntity(customerDTO);
		Long custId = customer.getCustId();
		String custMail = customer.getCustMail();
		String custPhone = customer.getCustPhone();
		if (customerDAO.isCustomerExists(custId)) {
			throw new DuplicateIdException("Customer with Id:" + custId + " already exists!");
		}
		if (customerDAO.getCustomerByMail(custMail) != null || customerDAO.getCustomerByPhoneNo(custPhone) != null) {
			throw new DuplicateIdException("User account already exists with same details!");
		}
		MailSend.sendMail(customer.getCustMail(), "Customer Registration",
				"Hi, " + customer.getCustName() + " Your account is created successfully!");
		return customerDAO.addCustomer(customer);
	}

	@Override
	public String updateCustomer(CustomerDTO customerDTO) {
		logger.info("update customer");
		Customer customer = CustomerMapper.dtoToEntity(customerDTO);
		Long custId = customer.getCustId();
		if (customerDAO.isCustomerExists(custId))
			return customerDAO.updateCustomer(customer);
		else
			throw new IdNotFoundException("Customer with Id:" + custId + " is not found");

	}

	@Override
	public String deleteCustomerById(Long custId) {
		logger.info("delete customer");
		if (customerDAO.isCustomerExists(custId))
			return customerDAO.deleteCustomerById(custId);
		else
			throw new IdNotFoundException("Customer with Id:" + custId + "is not found");
	}

	@Override
	public Customer getCustomerById(Long custId) {
		logger.info("getting customer by id");
		if (customerDAO.isCustomerExists(custId))
			return customerDAO.getCustomerById(custId);
		else
			throw new IdNotFoundException("User with Id:" + custId + " is not found");
	}

	@Override
	public List<Customer> getCustomerByName(String custName) {
		logger.info("getting customer by name");
		List<Customer> customers = customerDAO.getCustomerByName(custName);
		if (CollectionUtils.isEmpty(customers))
			throw new NullValueException("No datas Found");
		return customers;
	}

	@Override
	public Customer getCustomerByPhoneNo(String custPhone) {
		logger.info("getting customer by phonenumber");
		Customer customer = customerDAO.getCustomerByPhoneNo(custPhone);
		if (customer == null)
			throw new NullValueException("No datas Found");
		return customer;
	}

	@Override
	public Customer getCustomerByMail(String custMail) {
		logger.info("getting customer by mailid");
		Customer customer = customerDAO.getCustomerByMail(custMail);
		if (customer == null)
			throw new NullValueException("No datas found");
		else
			return customer;
	}

	@Override
	public boolean isCustomerExists(Long custId) {
		logger.info("checking existance of customer by id");
		return customerDAO.isCustomerExists(custId);
	}

	@Override
	public List<Customer> getAllCustomers() {
		logger.info("getting all customer");
		List<Customer> customers = customerDAO.getAllCustomers();
		if (CollectionUtils.isEmpty(customers))
			throw new NullValueException("No datas found");
		return customers;
	}

	@Override
	public Customer customerLogin(String custMail, String custPwd) {
		logger.info("customer login");
		Customer customer = customerDAO.customerLogin(custMail, custPwd);
		if (customer == null)
			throw new NullValueException("No record Found");
		return customer;
	}

	@Override
	public String forgotPassword(String custMail, String custPwd) {
		logger.info("forgot password");
		Customer customer = customerDAO.getCustomerByMail(custMail);
		if (customer != null)
			return customerDAO.forgotPassword(custMail, PasswordGenerator.generatePassword());
		else
			throw new IdNotFoundException("Customer not found");
	}

}
