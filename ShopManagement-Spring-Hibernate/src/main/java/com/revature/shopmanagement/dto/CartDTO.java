package com.revature.shopmanagement.dto;

import java.util.Date;

public class CartDTO {
	private long cartId;
	private Date cartAddedOn;
	private Date cartUpdatedOn;

	public CartDTO() {
	}

	public CartDTO(long cartId, Date cartAddedOn, Date cartUpdatedOn) {
		super();
		this.cartId = cartId;
		this.cartAddedOn = cartAddedOn;
		this.cartUpdatedOn = cartUpdatedOn;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public Date getCartAddedOn() {
		return cartAddedOn;
	}

	public void setCartAddedOn(Date cartAddedOn) {
		this.cartAddedOn = cartAddedOn;
	}

	public Date getCartUpdatedOn() {
		return cartUpdatedOn;
	}

	public void setCartUpdatedOn(Date cartUpdatedOn) {
		this.cartUpdatedOn = cartUpdatedOn;
	}

	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", cartAddedOn=" + cartAddedOn + ", cartUpdatedOn=" + cartUpdatedOn + "]";
	}

}
