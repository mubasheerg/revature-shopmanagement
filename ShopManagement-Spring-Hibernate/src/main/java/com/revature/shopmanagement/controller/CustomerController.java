package com.revature.shopmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.shopmanagement.dto.CustomerDTO;
import com.revature.shopmanagement.entity.Customer;
import com.revature.shopmanagement.service.CustomerService;

@CrossOrigin("*")

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	// method to get customer by id
	@GetMapping("/getCustomer/Id/{custId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("custId") Long custId) {
		Customer customer = new Customer();
		if (customerService.isCustomerExists(custId)) {
			customer = customerService.getCustomerById(custId);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} else
			return new ResponseEntity<>(customer, HttpStatus.NO_CONTENT);
	}

	// method to create a new customer
	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customer) {
		long custId = customer.getCustId();
		if (customerService.isCustomerExists(custId))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			customerService.addCustomer(customer);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	// method to update an existing customer
	@PutMapping("/updateCustomer")
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDTO customer) {
		long custId = customer.getCustId();
		if (customerService.isCustomerExists(custId)) {
			customerService.updateCustomer(customer);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// method to delete an existing customer
	@DeleteMapping("/deleteCustomer/{custId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("custId") Long custId) {
		if (customerService.isCustomerExists(custId)) {
			customerService.deleteCustomerById(custId);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// method to fetch customer by name
	@GetMapping("/getCustomer/Name/{custName}")
	public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable String custName) {
		return new ResponseEntity<>(customerService.getCustomerByName(custName), HttpStatus.OK);
	}

	// method to fetch customer by phone
	@GetMapping("/getCustomer/Phone/{custPhone}")
	public ResponseEntity<Customer> getCustomerByPhoneNo(@PathVariable String custPhone) {
		return new ResponseEntity<>(customerService.getCustomerByPhoneNo(custPhone), HttpStatus.OK);
	}

	// method to fetch customer by phone
	@GetMapping("/getCustomer/Mail/{custMail}")
	public ResponseEntity<Customer> getCustomerByMail(@PathVariable String custMail) {
		return new ResponseEntity<>(customerService.getCustomerByPhoneNo(custMail), HttpStatus.OK);
	}

	// method to fetch all customer
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
	}

}
