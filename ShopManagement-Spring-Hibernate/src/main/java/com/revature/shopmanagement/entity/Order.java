package com.revature.shopmanagement.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "shoporders_table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;

	@Column(nullable = false)
	private double amount;

	@Column
	@Temporal(TemporalType.DATE)
	private Date orderedOn;

	@ManyToOne
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_ORDER_CUSTOMERID"))
	private Customer customer;

	@OneToMany
	@JoinColumn(name = "product_id")
	private List<Product> product;

	public Order() {
	}

	public Order(long orderId, double amount, Date orderedOn, Customer customer, List<Product> product) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.orderedOn = orderedOn;
		this.customer = customer;
		this.product = product;
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

	public Date getOrderedOn() {
		return orderedOn;
	}

	public void setOrderedOn(Date orderedOn) {
		this.orderedOn = orderedOn;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", amount=" + amount + ", orderedOn=" + orderedOn + ", customer="
				+ customer + ", product=" + product + "]";
	}

}
