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

import com.revature.shopmanagement.dto.CartDTO;
import com.revature.shopmanagement.entity.Cart;
import com.revature.shopmanagement.entity.Customer;
import com.revature.shopmanagement.service.CartService;

@CrossOrigin("*")

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	CartService cartService;

	@GetMapping("/getCart/Id/{cartId}")
	public ResponseEntity<Cart> getCartById(@PathVariable("cartId") Long cartId) {
		Cart cart = new Cart();
		if (cartService.isCartExists(cartId)) {
			cart = cartService.getCartById(cartId);
			return new ResponseEntity<>(cart, HttpStatus.OK);
		} else
			return new ResponseEntity<>(cart, HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<String> addCart(@RequestBody CartDTO cart) {
		long cartId = cart.getCartId();
		if (cartService.isCartExists(cartId))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			cartService.addCart(cart);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PutMapping("/updateCart")
	public ResponseEntity<String> updateCart(@RequestBody CartDTO cart) {
		long cartId = cart.getCartId();
		if (cartService.isCartExists(cartId)) {
			cartService.updateCart(cart);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteCart/{cartId}")
	public ResponseEntity<String> deleteCartById(@PathVariable("cartId") Long cartId) {
		if (cartService.isCartExists(cartId)) {
			cartService.deleteCartById(cartId);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getAllCarts")
	public ResponseEntity<List<Cart>> getAllCartDetails() {
		return new ResponseEntity<>(cartService.getAllCartDetails(), HttpStatus.OK);
	}

}
