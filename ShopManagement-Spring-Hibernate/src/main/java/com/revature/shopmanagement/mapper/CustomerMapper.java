package com.revature.shopmanagement.mapper;

import com.revature.shopmanagement.dto.CustomerDTO;
import com.revature.shopmanagement.entity.Customer;

public class CustomerMapper {

	public static Customer dtoToEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setCustId(customerDTO.getCustId());
		customer.setCustName(customerDTO.getCustName());
		customer.setCustPhone(customerDTO.getCustPhone());
		customer.setCustAddress(customerDTO.getCustAddress());
		customer.setCustMail(customerDTO.getCustMail());
		customer.setCustPwd(customer.getCustPwd());
		customer.setCustCreatedOn(customerDTO.getCustCreatedOn());
		customer.setCustUpdatedOn(customerDTO.getCustUpdatedOn());
		
		return customer;
	}
}
