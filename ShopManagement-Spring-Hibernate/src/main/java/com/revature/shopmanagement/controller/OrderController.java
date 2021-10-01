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

import com.revature.shopmanagement.dto.OrderDTO;
import com.revature.shopmanagement.entity.Customer;
import com.revature.shopmanagement.entity.Order;
import com.revature.shopmanagement.service.OrderService;

@CrossOrigin("*")

@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("/getOrder/Id/{orderId}")
	public ResponseEntity<Order> getCustomerById(@PathVariable("orderId") Long orderId) {
		Order order = new Order();
		if (orderService.isOrderExists(orderId)) {
			order = orderService.getOrderById(orderId);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} else
			return new ResponseEntity<>(order, HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<String> addOrder(@RequestBody OrderDTO order) {
		long orderId = order.getOrderId();
		if (orderService.isOrderExists(orderId))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			orderService.addOrder(order);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PutMapping("/updateOrder")
	public ResponseEntity<String> updateOrder(@RequestBody OrderDTO order) {
		long orderId = order.getOrderId();
		if (orderService.isOrderExists(orderId)) {
			orderService.updateOrder(order);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long orderId) {
		if (orderService.isOrderExists(orderId)) {
			orderService.deleteOrderById(orderId);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getAllOrders")
	public ResponseEntity<List<Order>> getAllcustomers() {
		return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
	}
}
