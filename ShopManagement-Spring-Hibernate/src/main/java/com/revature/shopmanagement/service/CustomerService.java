package com.revature.shopmanagement.service;

import java.util.List;

import com.revature.shopmanagement.dto.CustomerDTO;
import com.revature.shopmanagement.entity.Customer;

public interface CustomerService {

	/**
	 * function to insert customer
	 * 
	 * @param customerDTO-object from CustomerDTO
	 * @return-acknowledgement of insertion
	 */
	public String addCustomer(CustomerDTO customerDTO);

	/**
	 * function to update customer
	 * 
	 * @param customerDTO
	 * @return-acknowledgement of updation
	 */
	public String updateCustomer(CustomerDTO customerDTO);

	/**
	 * function to delete customer by id
	 * 
	 * @param custId-primary key of the table
	 * @return-acknowledgement of deletion
	 */
	public String deleteCustomerById(Long custId);

	/**
	 * 
	 * @param custId
	 * @return-return the object of customer
	 */
	public Customer getCustomerById(Long custId);

	/**
	 * 
	 * @param custName-name of the customer
	 * @return-return the customer with respective name
	 */
	public List<Customer> getCustomerByName(String custName);

	/**
	 * 
	 * @param custPhone-phone number of the customer
	 * @return-return the customer with respective phone number
	 */
	public Customer getCustomerByPhoneNo(String custPhone);

	/**
	 * 
	 * @param custMail-mailid of the customer
	 * @return-return the customer with respective mailid
	 */
	public Customer getCustomerByMail(String custMail);

	/**
	 * 
	 * @param custId
	 * @return-true or false
	 */
	public boolean isCustomerExists(Long custId);
	
	/**
	 * 
	 * @return-entire data of customers
	 */
	public List<Customer> getAllCustomers();

	/**
	 * function to login
	 * 
	 * @param custMail
	 * @param custPwd
	 * @return-return the customer
	 */
	public Customer customerLogin(String custMail, String custPwd);

	
	public String forgotPassword(String custMail, String custPwd);

}
