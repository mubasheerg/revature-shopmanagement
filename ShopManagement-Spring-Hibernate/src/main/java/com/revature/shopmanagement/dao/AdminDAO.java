package com.revature.shopmanagement.dao;

import java.util.List;

import com.revature.shopmanagement.entity.Admin;

public interface AdminDAO {

	public Admin getAdminById(Long adminId);

	public List<Admin> getAdminByName(String adminName);

	public List<Admin> getAllAdmins();

	public boolean isAdminExists(Long adminId);

	public Admin adminLogin(Long adminId, String adminPwd);

	public String deleteAdminById(Long adminId);

	public String addAdmin(Admin admin);

	public String updateAdmin(Admin admin);
}
