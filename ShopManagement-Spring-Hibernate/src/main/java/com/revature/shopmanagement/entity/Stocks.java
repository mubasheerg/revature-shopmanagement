package com.revature.shopmanagement.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "shopstocks_table")
public class Stocks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long stockId;

	@Column
	private long count;

	@Column
	private Date stockAddedOn;

	@Column
	private Date stockUpdatedOn;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public Stocks() {
	}

	public Stocks(long stockId, long count, Date stockAddedOn, Date stockUpdatedOn, Product product) {
		super();
		this.stockId = stockId;
		this.count = count;
		this.stockAddedOn = stockAddedOn;
		this.stockUpdatedOn = stockUpdatedOn;
		this.product = product;
	}

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public Date getStockAddedOn() {
		return stockAddedOn;
	}

	public void setStockAddedOn(Date stockAddedOn) {
		this.stockAddedOn = stockAddedOn;
	}

	public Date getStockUpdatedOn() {
		return stockUpdatedOn;
	}

	public void setStockUpdatedOn(Date stockUpdatedOn) {
		this.stockUpdatedOn = stockUpdatedOn;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Stocks [stockId=" + stockId + ", count=" + count + ", stockAddedOn=" + stockAddedOn
				+ ", stockUpdatedOn=" + stockUpdatedOn + ", product=" + product + "]";
	}

}
