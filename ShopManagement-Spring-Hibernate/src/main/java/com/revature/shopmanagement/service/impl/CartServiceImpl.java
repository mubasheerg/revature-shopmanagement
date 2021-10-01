package com.revature.shopmanagement.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.shopmanagement.dao.CartDAO;
import com.revature.shopmanagement.dto.CartDTO;
import com.revature.shopmanagement.entity.Cart;
import com.revature.shopmanagement.entity.Product;
import com.revature.shopmanagement.exception.DuplicateIdException;
import com.revature.shopmanagement.exception.IdNotFoundException;
import com.revature.shopmanagement.exception.NullValueException;
import com.revature.shopmanagement.mapper.CartMapper;
import com.revature.shopmanagement.mapper.ProductMapper;
import com.revature.shopmanagement.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private static final Logger logger = LogManager.getLogger(CartServiceImpl.class);

	@Autowired
	private CartDAO cartDAO;

	@Override
	public String addCart(CartDTO cartDTO) {
		logger.info("add cart");
		Cart cart = CartMapper.dtoToEntity(cartDTO);
		Long cartId = cart.getCartId();
		if (cartDAO.isCartExists(cartId)) {
			throw new DuplicateIdException("Cart with Id :" + cartId + " already exists");
		}
		return cartDAO.addCart(cart);
	}

	@Override
	public String updateCart(CartDTO cartDTO) {
		logger.info("update cart");
		Cart cart = CartMapper.dtoToEntity(cartDTO);
		Long cartId = cart.getCartId();
		if (cartDAO.isCartExists(cartId))
			return cartDAO.updateCart(cart);
		else
			throw new IdNotFoundException("Cart with Id: " + cartId + " is not found");
	}

	@Override
	public String deleteCartById(Long cartId) {
		logger.info("delete cary by id");
		if (cartDAO.isCartExists(cartId))
			return cartDAO.deleteCartById(cartId);
		else
			throw new IdNotFoundException("Cart with Id: " + cartId + " is not found");
	}

	@Override
	public Cart getCartById(Long cartId) {
		logger.info("getting cart by id");
		if (cartDAO.isCartExists(cartId))
			return cartDAO.getCartById(cartId);
		else
			throw new IdNotFoundException("cart with Id: " + cartId + " is not found");
	}

	@Override
	public boolean isCartExists(Long cartId) {
		logger.info("checking existance of cart by id");
		return cartDAO.isCartExists(cartId);
	}

	@Override
	public List<Cart> getAllCartDetails() {
		logger.info("getting product by name");
		List<Cart> carts = cartDAO.getAllCartDetails();
		if (CollectionUtils.isEmpty(carts))
			throw new NullValueException("No datas Found");
		return carts;
	}

}
