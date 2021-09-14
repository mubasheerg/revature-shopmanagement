package com.revature.shopmanagement.dao;

import java.util.List;
import com.revature.shopmanagement.model.Customer;

public interface CustomerDAO {

	public String addCustomer(Customer customer);

	public String updateCustomer(Customer customer);

	public String deleteCustomerById(Long custId);

	public Customer getCustomerById(Long custId);

	public Customer getCustomerByName(String custName);

	public Customer getCustomerByPhoneNo(String custPhone);

	public boolean isCustomerExists(Long custId);

	public List<Customer> getAllCustomers();
}
