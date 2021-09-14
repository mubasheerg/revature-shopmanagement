package com.revature.shopmanagement.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "revcustomers")
@Component
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long custId;

	@Column(nullable = false)
	private String custName;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String custAddress;

	@Column(unique = true, nullable = false)
	private String custPhone;
	
	@Column
	private LocalDateTime custCreatedOn;
	@Column
	private LocalDateTime custUpdatedOn;

	public Customer() {

	}

	public Customer(long custId, String custName, String password, String custAddress, String custPhone,
			LocalDateTime custCreatedOn, LocalDateTime custUpdatedOn) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.password = password;
		this.custAddress = custAddress;
		this.custPhone = custPhone;
		this.custCreatedOn = custCreatedOn;
		this.custUpdatedOn = custUpdatedOn;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public LocalDateTime getCustCreatedOn() {
		return custCreatedOn;
	}

	public void setCustCreatedOn(LocalDateTime custCreatedOn) {
		this.custCreatedOn = custCreatedOn;
	}

	public LocalDateTime getCustUpdatedOn() {
		return custUpdatedOn;
	}

	public void setCustUpdatedOn(LocalDateTime custUpdatedOn) {
		this.custUpdatedOn = custUpdatedOn;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", password=" + password + ", custAddress="
				+ custAddress + ", custPhone=" + custPhone + ", custCreatedOn=" + custCreatedOn + ", custUpdatedOn="
				+ custUpdatedOn + "]";
	}

}
