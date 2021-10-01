package com.revature.shopmanagement.service;

import java.util.List;

import com.revature.shopmanagement.dto.AdminDTO;
import com.revature.shopmanagement.entity.Admin;

public interface AdminService {

	/**
	 * 
	 * @param adminId-primary key of the table
	 * @return-getting details of the admin with the given id
	 */
	public Admin getAdminById(Long adminId);

	/**
	 * 
	 * @param adminName-name of the admin
	 * @return-getting details of the admin with given name
	 */
	public List<Admin> getAdminByName(String adminName);

	/**
	 * 
	 * @return-fetch data of all the admins
	 */
	public List<Admin> getAllAdmins();

	/**
	 * 
	 * @param adminId
	 * @return-checks the existance of admin with given id
	 */
	public boolean isAdminExists(Long adminId);

	/**
	 * used to perform login function
	 * 
	 * @param adminId
	 * @param AdminPwd
	 * @return-it will return admin
	 */
	public Admin adminLogin(long adminId, String adminPwd);

	/**
	 * 
	 * @param adminId--it will delete the admin based on adminId
	 * @return--it will return a message deleted successfully
	 */
	public String deleteAdminById(Long adminId);

	/**
	 * it will add admin
	 * 
	 * @param adminDto
	 * @return--it will return a message added successfully
	 */
	public String addAdmin(AdminDTO adminDTO);

	/**
	 * it will update admin
	 * 
	 * @param adminDto
	 * @return--it will return a message updated successfully
	 */
	public String updateAdmin(AdminDTO adminDTO);

}
