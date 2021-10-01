package com.revature.shopmanagement.mapper;

import com.revature.shopmanagement.dto.CartDTO;
import com.revature.shopmanagement.entity.Cart;

public class CartMapper {
	public static Cart dtoToEntity(CartDTO cartDTO) {
		Cart cart = new Cart();
		cart.setCartId(cartDTO.getCartId());
		cart.setCartAddedOn(cartDTO.getCartAddedOn());
		cart.setCartUpdatedOn(cartDTO.getCartUpdatedOn());

		return cart;
	}
}
