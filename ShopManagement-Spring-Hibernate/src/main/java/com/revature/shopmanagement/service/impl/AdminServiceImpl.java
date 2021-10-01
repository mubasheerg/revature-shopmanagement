package com.revature.shopmanagement.service.impl;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.shopmanagement.dao.AdminDAO;
import com.revature.shopmanagement.dto.AdminDTO;
import com.revature.shopmanagement.entity.Admin;
import com.revature.shopmanagement.exception.DuplicateIdException;
import com.revature.shopmanagement.exception.IdNotFoundException;
import com.revature.shopmanagement.exception.NullValueException;
import com.revature.shopmanagement.mapper.AdminMapper;
import com.revature.shopmanagement.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminDAO adminDAO;

	@Override
	public Admin getAdminById(Long adminId) {
		logger.info("getting admin by id");
		if (adminDAO.isAdminExists(adminId)) {
			return adminDAO.getAdminById(adminId);
		}
		throw new IdNotFoundException("Admin with id: " + adminId + " not found");
	}

	@Override
	public List<Admin> getAdminByName(String adminName) {
		logger.info("getting all admin by given name");
		List<Admin> admins = adminDAO.getAdminByName(adminName);
		if (CollectionUtils.isEmpty(admins))
			throw new NullValueException("No datas found");
		return admins;
	}

	@Override
	public List<Admin> getAllAdmins() {
		logger.info("getting all admins");
		List<Admin> admins = adminDAO.getAllAdmins();
		if (CollectionUtils.isEmpty(admins))
			throw new NullValueException("No datas found");
		return admins;
	}

	@Override
	public boolean isAdminExists(Long adminId) {
		logger.info("Checking the existance of admin");
		return adminDAO.isAdminExists(adminId);
	}

	@Override
	public Admin adminLogin(long adminId, String adminPwd) {
		logger.info("admin login");
		return adminDAO.adminLogin(adminId, adminPwd);
	}

	@Override
	public String deleteAdminById(Long adminId) {
		logger.info("delete admin by id");
		if (adminDAO.isAdminExists(adminId)) {
			return adminDAO.deleteAdminById(adminId);
		}
		throw new IdNotFoundException("Admin Id:" + adminId + " Not Found to delete!");
}

	@Override
	public String addAdmin(AdminDTO adminDTO) {
		logger.info("add admin");
		Admin admin = AdminMapper.dtoToEntity(adminDTO);
		Long adminId = admin.getAdminId();
		if (adminDAO.isAdminExists(adminId)) {
			throw new DuplicateIdException("Admin with Id:" + adminId + " already exists!");
		}
		return adminDAO.addAdmin(admin);
	}

	@Override
	public String updateAdmin(AdminDTO adminDTO) {
		logger.info("update admin");
		Admin admin = AdminMapper.dtoToEntity(adminDTO);
		Long adminId = admin.getAdminId();
		if (adminDAO.isAdminExists(adminId)) {
			return adminDAO.updateAdmin(admin);
		}
		throw new IdNotFoundException("Admin Id:" + adminId + " Not Found to update!");
	}

}
