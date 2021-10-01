package com.revature.shopmanagement.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shopcustomers_table")

public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long custId;

	@Column(nullable = false)
	private String custName;

	@Column(unique = true, nullable = false)
	private String custMail;

	@Column(nullable = false)
	private String custPwd;

	@Column(nullable = false)
	private String custAddress;

	@Column(unique = true, nullable = false)
	private String custPhone;

	@Column
	@Temporal(TemporalType.DATE)
	private Date custCreatedOn;
	@Column

	@Temporal(TemporalType.DATE)
	private Date custUpdatedOn;

	@JsonIgnore
	@OneToMany
	private List<Order> order;

	public Customer() {

	}

	public Customer(long custId, String custName, String custMail, String custPwd, String custAddress, String custPhone,
			Date custCreatedOn, Date custUpdatedOn, List<Order> order) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custMail = custMail;
		this.custPwd = custPwd;
		this.custAddress = custAddress;
		this.custPhone = custPhone;
		this.custCreatedOn = custCreatedOn;
		this.custUpdatedOn = custUpdatedOn;
		this.order = order;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustMail() {
		return custMail;
	}

	public void setCustMail(String custMail) {
		this.custMail = custMail;
	}

	public String getCustPwd() {
		return custPwd;
	}

	public void setCustPwd(String custPwd) {
		this.custPwd = custPwd;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public Date getCustCreatedOn() {
		return custCreatedOn;
	}

	public void setCustCreatedOn(Date custCreatedOn) {
		this.custCreatedOn = custCreatedOn;
	}

	public Date getCustUpdatedOn() {
		return custUpdatedOn;
	}

	public void setCustUpdatedOn(Date custUpdatedOn) {
		this.custUpdatedOn = custUpdatedOn;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custMail=" + custMail + ", custPwd="
				+ custPwd + ", custAddress=" + custAddress + ", custPhone=" + custPhone + ", custCreatedOn="
				+ custCreatedOn + ", custUpdatedOn=" + custUpdatedOn + ", order=" + order + "]";
	}

}
