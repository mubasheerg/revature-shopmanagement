package com.revature.shopmanagement.mapper;

import com.revature.shopmanagement.dto.AdminDTO;
import com.revature.shopmanagement.entity.Admin;

public class AdminMapper {
	public static Admin dtoToEntity(AdminDTO adminDTO) {
		Admin admin = new Admin();
		admin.setAdminId(adminDTO.getAdminId());
		admin.setAdminName(adminDTO.getAdminName());
		admin.setAdminPwd(adminDTO.getAdminPwd());

		return admin;
	}
}
