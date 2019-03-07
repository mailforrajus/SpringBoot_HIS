	package com.his.service;
	
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
	
	import com.his.entity.ARUserMaster;
	import com.his.model.UserMaster;
	import com.his.repository.ArRepository;
	import com.his.util.AppConstants;
	import com.his.util.EmailService;
	import com.his.util.PasswordService;
	
	@Service
	public class ArService {
	
		@Autowired
		private ArRepository arUserMasterDao;
	
		@Autowired(required = true)
		private EmailService emailService;
	
		public UserMaster saveCaseWorkerInfo(UserMaster um) {
			ARUserMaster entity = new ARUserMaster();
	
			// Defaulting case worker as Active
			um.setActiveSw(AppConstants.STR_Y);
			um.setCreatedBy(AppConstants.ADMIN);
	
			// copying data from model to entity
			BeanUtils.copyProperties(um, entity);
	
			// Encrypting User Password
			String encryptedPwd = PasswordService.encrypt(um.getPwd());
			entity.setPwd(encryptedPwd);
	
			// Calling Repository method
			ARUserMaster savedEntity = arUserMasterDao.save(entity);
	
			// Sending Email with Pwd
			/*if (savedEntity != null) {
				String text;
				try {
					text = getRegEmailBody(um);
					emailService.sendEmail(um.getEmail(), AppConstants.EMAIL_FROM, AppConstants.EMAIL_SUBJECT, text);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}*/
	
			// setting generated pk value to model
			um.setUserId(savedEntity.getUserId());
			return um;
		}
	
		public boolean checkEmailExistance(String emailId) {
			boolean emailFoundStatus = false;
			int count = arUserMasterDao.checkEmailCount(emailId);
			if (count >= 1) {
				emailFoundStatus = true;
			}
			return emailFoundStatus;
		}
	
		private String getRegEmailBody(UserMaster um) throws Exception {
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
					line = line.replace("APP_URL", "<a href='http://localhost:4200/'>RI HIS</a>");
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
	
		public Page<ARUserMaster> findAllUsers(int pageNo, int pageSize) {
			Pageable pageble = new PageRequest(pageNo, pageSize);
			List<UserMaster> users = new ArrayList<UserMaster>();
			Page<ARUserMaster> pages = arUserMasterDao.findAll(pageble);
			return pages;
		}
	
		public void activateCaseWorkerUsingEmailId(String emailId) {
			// TODO Auto-generated method stub
			ARUserMaster resultArUserMaster=arUserMasterDao.findByEmailId(emailId);
			resultArUserMaster.setActiveSw(AppConstants.ACTIVATE_CASEWORKER_STATUS);
			System.out.println(resultArUserMaster);
			arUserMasterDao.save(resultArUserMaster);
		}
		
		public void deActivateCaseWorkerUsingEmailId(String emailId) {
			// TODO Auto-generated method stub
			ARUserMaster resultArUserMaster=arUserMasterDao.findByEmailId(emailId);
			System.out.println("--"+resultArUserMaster);
			resultArUserMaster.setActiveSw(AppConstants.DEACTIVATE_CASEWORKER_STATUS);
			System.out.println(resultArUserMaster);
			arUserMasterDao.save(resultArUserMaster);
		}
	
		public List<ARUserMaster> findAllCaseWorkers() {
			// TODO Auto-generated method stub
			return arUserMasterDao.findAll();
		}
	
		public UserMaster getUserModelUsingUserId(int userId) {
			UserMaster userMaster= new UserMaster();
			ARUserMaster arUserMaster=arUserMasterDao.findById(userId).get();
			BeanUtils.copyProperties(arUserMaster, userMaster);
			userMaster.setPwd(PasswordService.decrypt(userMaster.getPwd()));
			return userMaster;
		}
	
		public boolean validateUserCredantials(String userName, String password) {
			
			String encryptedPassWord=PasswordService.encrypt(password);
			ARUserMaster arUserMaster=arUserMasterDao.findByEmailIdAndPassword(userName, encryptedPassWord);
			
			// TODO Auto-generated method stub
			return false;
		}
	
	}
