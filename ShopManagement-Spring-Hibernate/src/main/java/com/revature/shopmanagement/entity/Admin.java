package com.revature.shopmanagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "shopadmins_table")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;

	@Column(unique = true, nullable = false)
	private String adminName;

	@Column(unique = true, nullable = false)
	private String mailId;

	@Column(unique = true, nullable = false)
	private String adminPwd;

	public Admin() {
	}

	public Admin(Long adminId, String adminName, String mailId, String adminPwd) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.mailId = mailId;
		this.adminPwd = adminPwd;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", mailId=" + mailId + ", adminPwd="
				+ adminPwd + "]";
	}

}
