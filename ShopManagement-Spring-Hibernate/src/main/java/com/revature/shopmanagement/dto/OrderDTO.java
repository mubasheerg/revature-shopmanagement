package com.revature.shopmanagement.dto;

import java.util.Date;

public class OrderDTO {
	private long orderId;
	private double amount;
	private Date orderAddedOn;

	public OrderDTO() {
	}

	public OrderDTO(long orderId, double amount, Date orderAddedOn) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.orderAddedOn = orderAddedOn;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getOrderAddedOn() {
		return orderAddedOn;
	}

	public void setOrderAddedOn(Date orderAddedOn) {
		this.orderAddedOn = orderAddedOn;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", amount=" + amount + ", orderAddedOn=" + orderAddedOn + "]";
	}

}
