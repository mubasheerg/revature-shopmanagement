package com.revature.shopmanagement.dao;

import java.util.List;
import com.revature.shopmanagement.entity.Cart;

public interface CartDAO {
	public String addCart(Cart cart);

	public String updateCart(Cart cart);

	public String deleteCartById(Long cartId);

	public Cart getCartById(Long custId);

	public boolean isCartExists(Long cartId);

	public List<Cart> getAllCartDetails();
}
