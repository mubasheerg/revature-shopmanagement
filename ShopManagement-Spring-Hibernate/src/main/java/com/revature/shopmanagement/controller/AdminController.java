package com.revature.shopmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.shopmanagement.dto.AdminDTO;
import com.revature.shopmanagement.entity.Admin;
import com.revature.shopmanagement.service.AdminService;

@CrossOrigin("*")

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	// admin login
	@GetMapping("login/{adminId}/{adminPwd}")
	public ResponseEntity<Admin> adminLogin(@PathVariable("adminId") Long adminId,
			@PathVariable("adminPassword") String adminPwd) {
		return new ResponseEntity<>(adminService.adminLogin(adminId, adminPwd), HttpStatus.OK);
	}

	// insert admin
	@PostMapping
	public ResponseEntity<String> addAdmin(@RequestBody AdminDTO adminDTO) {
		return new ResponseEntity<>(adminService.addAdmin(adminDTO), HttpStatus.OK);
	}

	// update admin
	@PutMapping
	public ResponseEntity<String> updateAdmin(@RequestBody AdminDTO adminDTO) {
		return new ResponseEntity<>(adminService.updateAdmin(adminDTO), HttpStatus.OK);

	}
	
	// delete admin
	@DeleteMapping("/{adminId}")
	public ResponseEntity<String> deleteAdmin(@PathVariable("adminId") Long adminId) {
		return new ResponseEntity<>(adminService.deleteAdminById(adminId), HttpStatus.OK);

	}

	@GetMapping("/getAdmin/Id/{adminId}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") Long adminId) {
		Admin admin = new Admin();
		if (adminService.isAdminExists(adminId)) {
			admin = adminService.getAdminById(adminId);
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} else
			return new ResponseEntity<>(admin, HttpStatus.NO_CONTENT);
	}

	@GetMapping("getAdmin/Name/{adminName}")
	public ResponseEntity<List<Admin>> getAdminByName(@PathVariable String adminName) {
		return new ResponseEntity<>(adminService.getAdminByName(adminName), HttpStatus.OK);
	}

	@GetMapping("/getAllAdmins")
	public ResponseEntity<List<Admin>> getAllAdmins() {
		return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
	}
}
