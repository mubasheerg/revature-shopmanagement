package com.revature.shopmanagement.dao;

import java.util.List;

import com.revature.shopmanagement.entity.Customer;

public interface CustomerDAO {

	public String addCustomer(Customer customer);

	public String updateCustomer(Customer customer);

	public String deleteCustomerById(Long custId);

	public Customer getCustomerById(Long custId);

	public List<Customer> getCustomerByName(String custName);

	public Customer getCustomerByPhoneNo(String custPhone);

	public Customer getCustomerByMail(String custMail);

	public boolean isCustomerExists(Long custId);

	public List<Customer> getAllCustomers();
	
	public Customer customerLogin(String custMail, String custPwd);

	public String forgotPassword(String custMail,String custPwd);


}
