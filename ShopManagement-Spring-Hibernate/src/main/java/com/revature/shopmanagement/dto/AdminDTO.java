package com.revature.shopmanagement.dto;

public class AdminDTO {
	private Long adminId;
	private String adminName;
	private String mailId;
	private String adminPwd;

	public AdminDTO() {
	}

	public AdminDTO(Long adminId, String adminName, String mailId, String adminPwd) {
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
		return "AdminDTO [adminId=" + adminId + ", adminName=" + adminName + ", mailId=" + mailId + ", adminPwd="
				+ adminPwd + "]";
	}

}
