package com.revature.shopmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.shopmanagement.controller.MailSend;
import com.revature.shopmanagement.dao.AdminDAO;
import com.revature.shopmanagement.entity.Admin;
import com.revature.shopmanagement.exception.DataBaseException;
import com.revature.shopmanagement.util.PasswordGenerator;

@Repository
public class AdminDAOImpl implements AdminDAO {
	private static final Logger logger = LogManager.getLogger(AdminDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	private static final String GET_ADMIN_BY_NAME = "select a from Admin a where a.adminName=?1";
	private static final String GET_ALL_ADMINS = "select a from Admin a";
	private static final String ADMIN_LOGIN = "select a from Admin a where a.adminId=?1 and a.adminPwd=?2";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Admin getAdminById(Long adminId) {
		logger.info("getting admin by id");
		Session session = sessionFactory.getCurrentSession();
		return session.get(Admin.class, adminId);
	}

	@Override
	public List<Admin> getAdminByName(String adminName) {
		logger.info("getting admin by name");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Admin> resultList = session.createQuery(GET_ADMIN_BY_NAME, Admin.class).setParameter(1, adminName)
					.getResultList();
			return (resultList.isEmpty() ? null : (List<Admin>) resultList.get(0));
		} catch (Exception e) {
			throw new DataBaseException("Error in dataase");
		}
	}

	@Override
	public List<Admin> getAllAdmins() {
		logger.info("Entering get all admins Function");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Admin> query = session.createQuery(GET_ALL_ADMINS, Admin.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}
	}

	@Override
	public boolean isAdminExists(Long adminId) {
		logger.info("checking existance of admin");
		Session session = sessionFactory.getCurrentSession();
		Admin admin = session.get(Admin.class, adminId);
		return (admin != null);
	}

	@Override
	public Admin adminLogin(Long adminId, String adminPwd) {
		logger.info("admin login");
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Admin> resultList = session.createQuery(ADMIN_LOGIN, Admin.class).setParameter(1, adminId)
					.setParameter(2, adminPwd).getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			throw new DataBaseException("Error in database");
		}

	}

	@Transactional
	@Override
	public String deleteAdminById(Long adminId) {
		logger.info("delete admin by id");
		Admin admin = getAdminById(adminId);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(admin);
			return "Admin deleted with : " + adminId + " at " + localTime;
		} catch (Exception e) {
			throw new DataBaseException("Error in deleting admin from DataBase");
		}
	}

	@Transactional
	@Override
	public String addAdmin(Admin admin) {
		logger.info("Entering add Admin Function");
		try {
			Session session = sessionFactory.getCurrentSession();
			admin.setAdminPwd(PasswordGenerator.generatePassword());
			session.save(admin);
			Long adminId = admin.getAdminId();
			MailSend.sendMail(admin.getMailId(), "Admin Registration",
					"Hi, " + admin.getAdminName() + "\nYou're added as admin" + "\nAdmin Id :" + admin.getAdminId()
							+ "\nNew Password :" + admin.getAdminPwd() + "\n\nThank You.");
			return "Admin Account created with : " + adminId + " at " + localTime;
		} catch (Exception e) {
			throw new DataBaseException("Error in adding admin to DataBase");
		}
	}

	@Override
	public String updateAdmin(Admin admin) {
		logger.info("Entering update Admin  Function");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(admin);
			return "Admin details updated successfully!";
		} catch (Exception e) {
			throw new DataBaseException("Error in updating admin to DataBase");
		}
	}
}
