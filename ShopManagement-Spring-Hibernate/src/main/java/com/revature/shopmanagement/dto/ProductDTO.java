package com.revature.shopmanagement.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class ProductDTO {
	private long prodId;
	private String prodName;
	private double prodPrice;
	private String category;
	private Date prodAddedOn;
	private Date prodUpdatedOn;

	public ProductDTO() {
	}

	public ProductDTO(long prodId, String prodName, double prodPrice, String category, Date prodAddedOn,
			Date prodUpdatedOn) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.category = category;
		this.prodAddedOn = prodAddedOn;
		this.prodUpdatedOn = prodUpdatedOn;
	}

	public long getProdId() {
		return prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public double getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getProdAddedOn() {
		return prodAddedOn;
	}

	public void setProdAddedOn(Date prodAddedOn) {
		this.prodAddedOn = prodAddedOn;
	}

	public Date getProdUpdatedOn() {
		return prodUpdatedOn;
	}

	public void setProdUpdatedOn(Date prodUpdatedOn) {
		this.prodUpdatedOn = prodUpdatedOn;
	}

	@Override
	public String toString() {
		return "ProductDTO [prodId=" + prodId + ", prodName=" + prodName + ", prodPrice=" + prodPrice + ", category="
				+ category + ", prodAddedOn=" + prodAddedOn + ", prodUpdatedOn=" + prodUpdatedOn + "]";
	}

	
}
