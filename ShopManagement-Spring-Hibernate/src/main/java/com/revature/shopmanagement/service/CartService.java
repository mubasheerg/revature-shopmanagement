package com.revature.shopmanagement.service;

import java.util.List;

import com.revature.shopmanagement.dto.CartDTO;
import com.revature.shopmanagement.entity.Cart;

public interface CartService {
	public String addCart(CartDTO cartDTO);

	public String updateCart(CartDTO cartDTO);

	public String deleteCartById(Long cartId);

	public Cart getCartById(Long cartId);

	public boolean isCartExists(Long cartId);

	public List<Cart> getAllCartDetails();
}
