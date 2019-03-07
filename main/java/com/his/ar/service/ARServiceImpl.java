package com.his.ar.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.his.ar.dao.ARUserMasterDao;
import com.his.ar.entity.ARUserMaster;
import com.his.ar.model.UserMaster;
import com.his.util.AppConstants;
import com.his.util.EmailService;
import com.his.util.PasswordService;

/***
 * this class is used to business operation in the case worker
 * 
 * @author Nitish
 *
 */
@Service("arService")
public class ARServiceImpl implements ARService {

	@Autowired(required = true)
	private ARUserMasterDao arUserMasterDao;

	@Autowired(required = true)
	private EmailService emailService;

	/**
	 * This method is used to save the user record
	 */
	@Override
	public UserMaster saveUser(UserMaster um) {
		ARUserMaster entity = new ARUserMaster();

		um.setActiveSw(AppConstants.ACTIVE);
		um.setCreatedBy(AppConstants.ROLE);

		// copying data from model to entity
		BeanUtils.copyProperties(um, entity);

		// Encrypting User Password
		String encryptedPwd = PasswordService.encrypt(um.getPwd());
		entity.setPwd(encryptedPwd);

		ARUserMaster savedEntity = arUserMasterDao.save(entity);

		// Sending Email with Pwd
		if (savedEntity != null) {
			String text;
			try {
				text = getRegEmailBody(um);
				emailService.sendEmail(um.getEmail(), AppConstants.EMAIL_FROM, AppConstants.EMAIL_SUBJECT, text);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// setting generated pk value to model
		um.setUserId(savedEntity.getUserId());

		return um;
	}

	/***
	 *
	 * this method is used to login case worker when the case worker user is
	 * activated
	 */
	@Override
	public UserMaster findUserByEmailAndPwd(String email, String pwd) {
		UserMaster um = null;
		String encryptedPwd = PasswordService.encrypt(pwd);
		ARUserMaster arUserMaster = arUserMasterDao.findUserByEmailAndPwd(email, encryptedPwd);
		if (arUserMaster != null) {
			um = new UserMaster();
			BeanUtils.copyProperties(arUserMaster, um);

		}

		return um;
	}

	/**
	 * this method is used to registered for case worker with send email body
	 * 
	 * @param um
	 * @return
	 * @throws Exception
	 */
	public String getRegEmailBody(UserMaster um) throws Exception {
		String fileName = "Registration_Email_Template.txt";
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		StringBuilder mailBody = new StringBuilder("");
		while (line != null) {

			// Processing mail body content
			if (line.contains("USER_NAME")) {
				line = line.replace("USER_NAME", um.getFirstName() + " " + um.getLastName());
			}

			if (line.contains("APP_USER_EMAIL")) {
				line = line.replace("APP_USER_EMAIL", um.getEmail());
			}

			if (line.contains("APP_URL")) {
				line = line.replace("APP_URL",
						"<a href='http://localhost:2019/his'>Rhode Island Health Insurance System</a>");
			}

			if (line.contains("APP_USER_PWD")) {
				line = line.replace("APP_USER_PWD", um.getPwd());
			}

			// Appending processed line to StringBuilder
			mailBody.append(line);

			// reading next line
			line = br.readLine();
		}

		fr.close();
		br.close();

		// Returning mail body content
		return mailBody.toString();
	}

	/**
	 * this method is use to check duplicate email id
	 */
	@Override
	public UserMaster findByEmail(String email) {
		UserMaster um = null;
		ARUserMaster entity = arUserMasterDao.findByEmail(email);
		if (entity != null) {
			um = new UserMaster();

			BeanUtils.copyProperties(entity, um);

			if (AppConstants.ACTIVE.equals(entity.getActiveSw())) {
				um.setPwd(PasswordService.decrypt(entity.getPwd()));

				String text;
				try {
					text = getPassEmailBody(um);
					emailService.sendEmail(um.getEmail(), AppConstants.EMAIL_FROM, AppConstants.EMAIL_SUBJECT, text);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return um;
	}

	/***
	 * this method is used to sent email body when the case worker has been forget
	 * the password
	 * 
	 * @param um
	 * @return
	 * @throws Exception
	 */
	public String getPassEmailBody(UserMaster um) throws Exception {
		String fileName = "Pass_Forget_Template.txt";
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		StringBuilder mailBody = new StringBuilder("");
		while (line != null) {

			// Processing mail body content
			if (line.contains("USER_NAME")) {
				line = line.replace("USER_NAME", um.getFirstName() + " " + um.getLastName());
			}

			if (line.contains("APP_URL")) {
				line = line.replace("APP_URL",
						"<a href='http://localhost:2019/his'>Rhode Island Health Insurance System</a>");
			}

			if (line.contains("APP_USER_PWD")) {
				line = line.replace("APP_USER_PWD", um.getPwd());
			}

			// Appending processed line to StringBuilder
			mailBody.append(line);

			// reading next line
			line = br.readLine();
		}

		fr.close();
		br.close();

		// Returning mail body content
		return mailBody.toString();

		/**
		 * this method is used to all the records from db using pagination
		 */
	}

	@Override
	public Page<ARUserMaster> findAllUsers(int pageNo, int pageSize) {
		Pageable pageble = new PageRequest(pageNo, pageSize);
		List<UserMaster> users = new ArrayList<UserMaster>();
		Page<ARUserMaster> pages = arUserMasterDao.findAll(pageble);
		return pages;

	}

	/**
	 * This method is used to fetch CW record using userid
	 */
	@Override
	public UserMaster findById(Integer userId) {
		ARUserMaster entity = arUserMasterDao.findById(userId).get();
		UserMaster model = new UserMaster();
		BeanUtils.copyProperties(entity, model);
		return model;
	}

	/**
	 * this method is used to all the use from db
	 */
	@Override
	public List<UserMaster> findAllUsers() {

		List<UserMaster> users = new ArrayList<UserMaster>();
		List<ARUserMaster> entities = arUserMasterDao.findAll();
		for (ARUserMaster entity : entities) {
			UserMaster master = new UserMaster();
			BeanUtils.copyProperties(entity, master);
			users.add(master);
		}
		return users;
	}

	/**
	 * this method is used to case worker edit the profiles
	 */
	@Override
	public UserMaster findByIdEdit(int cid) {
		ARUserMaster entity = arUserMasterDao.findById(cid).get();
		UserMaster model = new UserMaster();

		BeanUtils.copyProperties(entity, model);
		model.setPwd(PasswordService.decrypt(entity.getPwd()));
		return model;
	}

	@Override
	public UserMaster saveUserUpadte(UserMaster um, boolean flage) {
		ARUserMaster entity = new ARUserMaster();
		um.setUpdatedBy(AppConstants.ROLE);
		// copying data from model to entity
		BeanUtils.copyProperties(um, entity);

		// Encrypting User Password
		if (flage != false) {
			String encryptedPwd = PasswordService.encrypt(um.getPwd());
			entity.setPwd(encryptedPwd);
		}
		ARUserMaster savedEntity = arUserMasterDao.save(entity);

		um.setUserId(savedEntity.getUserId());

		return um;
	}

}
