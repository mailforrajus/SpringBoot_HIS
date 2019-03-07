package com.his.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.his.entity.ARUserMaster;
import com.his.model.UserMaster;
import com.his.service.ArService;
import com.his.util.AppConstants;
//http://127.0.0.1:50807/browser/
@RestController
public class ARController {

	@Autowired
	private ArService arService;

	@CrossOrigin
	@PostMapping(value = "/checkEmailId", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,	MediaType.TEXT_PLAIN_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public boolean emailExistane(@RequestBody String emailId) {

		System.out.println(emailId);
		boolean status = arService.checkEmailExistance(emailId);
		System.out.println(status);
		return status;
	}
	

	@GetMapping(value="/welcome")
	public String welcome() {
		
		
		return "welcome";
	}

	@CrossOrigin
	@PostMapping(value="/saveCaseWorkerInfo", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,	MediaType.TEXT_PLAIN_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ResponseBody
	public Map<String, Boolean> saveCaseWorkerInfo(@RequestBody UserMaster master  ) {
		System.out.println("ARController.saveCaseWorkerInfo()");
		Map<String, Boolean> resu= new HashMap<>();
		UserMaster resultUserMaster=arService.saveCaseWorkerInfo(master);
		
		if(resultUserMaster.getUserId()!=null) {
			resu.put("status", true);
		}else {
			resu.put("status", false);
		}
		return resu;
	}
	
	@CrossOrigin
	@GetMapping(value="/displayAllCaseWorkerInfo",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE , MediaType.ALL_VALUE})
	@ResponseBody
	public Map<String, Object> getAllCaseWorkers(@RequestParam(name = "cpn", defaultValue = "1") String pageNo){
		System.out.println("ARController.getAllCaseWorkers()");
		Integer currentPageNo = 1;
		int pageNumber=Integer.parseInt(pageNo);
		List<UserMaster> users = new ArrayList<>();
		Map<String, Object> resultData= new HashMap<>();
		if (null != "1" && !"".equals("1")) {
			currentPageNo = pageNumber;
		}

		Page<ARUserMaster> page = arService.findAllUsers(currentPageNo - 1, AppConstants.PAGE_SIZE);
		int totalPages = page.getTotalPages();
		List<ARUserMaster> entities = page.getContent();

		for (ARUserMaster entity : entities) {
			UserMaster um = new UserMaster();
			BeanUtils.copyProperties(entity, um);
			users.add(um);
		}

		resultData.put("cpn", pageNumber);
		resultData.put("tp", totalPages);
		resultData.put("caseWorkers", users);

		
		return resultData;
		
	}
	
	@CrossOrigin
	@GetMapping(value="/activateCaseWorker")
	public boolean activateCaseWorker(@RequestParam("emaildId") String emailId) {
		boolean statusChanged=false;
		System.out.println("entereed email id "+emailId);
		if(emailId!=null && !emailId.equals(""))
		{
			System.out.println("inside");
			arService.activateCaseWorkerUsingEmailId(emailId);
			statusChanged=true;
		}
		return statusChanged;
	}
	
	@CrossOrigin
	@GetMapping(value="/deActivateCaseWorker")
	public boolean deactivateCaseWorker(@RequestParam("emaildId") String emailId) {
		boolean statusChanged=false;
		System.out.println("entereed email id "+emailId);
		if(emailId!=null && !emailId.equals(""))
		{
			System.out.println("inside");
			arService.deActivateCaseWorkerUsingEmailId(emailId);
			statusChanged=true;
		}
		return statusChanged;
	}

	@CrossOrigin
	@GetMapping(value = "/displayAllCaseWorkers", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE }, consumes = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE })
	@ResponseBody
	public List<UserMaster> getAllCaseWorkersInfo() {
		System.out.println("ARController.getAllCaseWorkers()");
		List<UserMaster> users = new ArrayList<>();

		List<ARUserMaster> entities = arService.findAllCaseWorkers();
		for (ARUserMaster entity : entities) {
			UserMaster um = new UserMaster();
			BeanUtils.copyProperties(entity, um);
			users.add(um);
		}
		return users;
	}
	
	@CrossOrigin
	@GetMapping(value = "/getCaseWorkerDetailsUsigUserId")
	public UserMaster getCaseWorkersInfoUsingEmailId(@RequestParam("userId") String userId ) {
		System.out.println("user id is"+userId);	
		UserMaster userMaseter= arService.getUserModelUsingUserId(Integer.parseInt(userId));
		return userMaseter;
	}
	
	
	@CrossOrigin
	@PostMapping(value="/updateCaseWorkerInfo", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,	MediaType.TEXT_PLAIN_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ResponseBody
	public boolean updateCaseWorkerInfo(@RequestBody UserMaster master  ) {
		System.out.println("ARController.saveCaseWorkerInfo()");
		UserMaster resultUserMaster=arService.saveCaseWorkerInfo(master);
		boolean status=false;
		if(resultUserMaster.getUserId()!=null) {
			status=true;
		}
		return status;
	}
	
	@CrossOrigin
	@GetMapping(value = "/validateUserCredantials")
	public boolean validateCaseWorkerCredantials(@RequestParam("userName") String userName , @RequestParam("password") String password) {
		boolean validStatus=false;	
		if(userName!=null && password!=null) {
			validStatus= arService.validateUserCredantials(userName, password);
		}
		return validStatus;
	}
	
}
