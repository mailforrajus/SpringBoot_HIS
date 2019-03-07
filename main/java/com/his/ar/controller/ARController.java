package com.his.ar.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.ar.entity.ARUserMaster;
import com.his.ar.model.UserMaster;
import com.his.ar.service.ARService;
import com.his.co.batches.CoPlanReportDlyBatch;
import com.his.util.AppConstants;

/**
 * this class is controller class
 * 
 * @author Nitish
 *
 */
@Controller
public class ARController {

	@Autowired
	private CoPlanReportDlyBatch batch;

	@Autowired
	private ARService arService;

	@RequestMapping({ "/batchTest" })
	public String batchTest() {
		System.out.println("ARController.batchTest()");
		batch.init();
		return "index";
	}

	/**
	 * this is used for home page and login page(admin / case worker)
	 * 
	 * @param us
	 * @param model
	 * @return string
	 */
	@RequestMapping({ "/", "/index" })
	public String indexs(@ModelAttribute("login") UserMaster us, Model model) {
		model.addAttribute("login", new UserMaster());
		return "index";
	}

	/***
	 * this method is used for Admin login or case worker user
	 * 
	 * @param um
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String AdminAndCaseWorker(@ModelAttribute("login") UserMaster user, Model model) {
		String view = "index";
		return authorized(user, model, view);

	}

	/** Authorized method of admin and case worker */
	private String authorized(UserMaster user, Model model, String view) {
		UserMaster um = arService.findUserByEmailAndPwd(user.getEmail(), user.getPwd());
		if (um == null) {
			model.addAttribute(AppConstants.ERROR, AppConstants.LOGIN_INVALID);
		} else {
			if (AppConstants.ROLE.equals(um.getRole())) {
				return "dashboardAdmin";
			} else {
				if (AppConstants.ACTIVE.equals(um.getActiveSw())) {
					return "dashboardCaseWorker";
				} else {
					model.addAttribute(AppConstants.ERROR, AppConstants.DEATIVATE);
				}
			}
		}
		return view;
	}

	/**
	 * this method is used for LogOut admin or case worker user
	 * 
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "/logout")
	public String forGetout(HttpSession session) {
		session.invalidate();
		return "redirect:/index?ac=lo";
	}

	/**
	 * this method is used for launch the creation case worker form
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/userReg", method = RequestMethod.GET)
	public String regPageLaunch(Model model) {
		initValue(model);
		model.addAttribute("userReg", new UserMaster());
		return "userReg";
	}

	/***
	 * this method is used for role in appls.
	 * 
	 * @param model
	 */
	private void initValue(Model model) {
		List<String> rol = new ArrayList<String>();

		rol.add("Case Worker");
		rol.add("Admin");
		model.addAttribute("rols", rol);
	}

	/**
	 * this method is used for submit the case worker form
	 * 
	 * @param us
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/userReg", method = RequestMethod.POST)
	public String regPage(@ModelAttribute("userReg") UserMaster us, Model model) {
		UserMaster master = arService.saveUser(us);
		initValue(model);
		if (master.getUserId() != null) {
			model.addAttribute(AppConstants.SUCCESS, AppConstants.REG_SUCCESS);
		} else {
			model.addAttribute(AppConstants.ERROR, AppConstants.REG_ERROR);
		}
		return "userReg";
	}

	/**
	 * this method is used for check the unique eamil id for asynchronous type
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/userReg/checkEmail")
	public @ResponseBody String checkUniqueEmail(@RequestParam(name = "email") String email) {
		UserMaster um = arService.findByEmail(email);
		return (um != null) ? AppConstants.DUPLICATE : AppConstants.UNIQUE;
	}

	/**
	 * this method is used for forget password
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/forgetPass", method = RequestMethod.GET)
	public String showLoginPage(Model model) {
		return "forget";
	}

	/**
	 * this method is used for forget password
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/forgetPass", method = RequestMethod.POST)
	public String forgetPass(HttpServletRequest req, Model model) {
		return passwordRecover(req, model);

	}

	/**
	 * password recover
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	private String passwordRecover(HttpServletRequest req, Model model) {
		String view = "forget";
		String email = req.getParameter("email");
		UserMaster um = arService.findByEmail(email);
		if (um == null) {
			model.addAttribute(AppConstants.ERROR, AppConstants.NOTSEND);
			return view;
		} else {
			if (AppConstants.ACTIVE.equals(um.getActiveSw())) {

				model.addAttribute(AppConstants.SUCCESS, AppConstants.SEND);
				return view;
			} else {
				model.addAttribute(AppConstants.ERROR, AppConstants.DEATIVATE);
				return view;
			}
		}
	}

	/**
	 * This method is used to retrieve all case workers
	 */
	@RequestMapping(value = "/viewCaseWorker")
	public String viewCaseWorkers(Model model) {
		List<UserMaster> users = new ArrayList<UserMaster>();
		// calling Service layer method
		users = arService.findAllUsers();
		// Storing data in model scope to access in view
		model.addAttribute("caseWorkers", users);
		return "viewCaseWorker";
	}

	/**
	 * this method is used to retrieve the all records
	 */
	@RequestMapping(value = "/listCaseWorker", method = RequestMethod.GET)
	public String listAllCaseWorkers(@RequestParam(name = "cpn", defaultValue = "1") String pageNo, Model model) {

		Integer currentPageNo = 1;
		List<UserMaster> users = new ArrayList<UserMaster>();
		if (null != pageNo && !"".equals(pageNo)) {
			currentPageNo = Integer.parseInt(pageNo);
		}

		// calling Service layer method
		Page<ARUserMaster> page = arService.findAllUsers(currentPageNo - 1, AppConstants.PAGE_SIZE);

		// Getting Total Pages required
		int totalPages = page.getTotalPages();

		// Getting page specific records
		List<ARUserMaster> entities = page.getContent();

		// Converting Entity objects Model objects
		for (ARUserMaster entity : entities) {
			UserMaster um = new UserMaster();
			BeanUtils.copyProperties(entity, um);
			users.add(um);
		}
		// Storing data in model scope to access in view
		model.addAttribute("cpn", pageNo);
		model.addAttribute("tp", totalPages);
		model.addAttribute("caseWorker", users);
		return "listCaseWorker";
	}

	/**
	 * This method is used to activate case worker
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/activateCwProfile")
	public String activateCwProfile(@RequestParam("uid") String userId) {
		try {
			if (null != userId && !"".equals(userId)) {
				int uid = Integer.parseInt(userId);
				UserMaster model = arService.findById(uid);
				// making profile as active
				model.setActiveSw(AppConstants.ACTIVE);
				// updating record
				arService.saveUserUpadte(model, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:listCaseWorker";
	}

	/**
	 * This method is used to perform soft delete of case worker
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/deleteCwProfile")
	public String deleteCwProfile(@RequestParam("uid") String userId) {
		try {
			if (null != userId && !"".equals(userId)) {
				int uid = Integer.parseInt(userId);
				UserMaster model = arService.findById(uid);
				// making profile as active
				model.setActiveSw(AppConstants.STR_N);
				// updating record
				arService.saveUserUpadte(model, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:listCaseWorker";
	}

	/**
	 * this method is used to case worker edit the profiles
	 */
	@RequestMapping(value = "/editCW", method = RequestMethod.GET)
	public String EditCW(@RequestParam("uid") String uid, @ModelAttribute("editCW") UserMaster user, Model model) {
		user = arService.findByIdEdit(Integer.parseInt(uid));
		initValue(model);
		model.addAttribute("editCW", user);
		return "editCaseWorker";

	}

	/**
	 * this method is used to case worker edit the profiles
	 */
	@RequestMapping(value = "/editCaseWorker", method = RequestMethod.POST)
	public String updateCW(@ModelAttribute("editCW") UserMaster us, Model model) {
		UserMaster master = arService.saveUserUpadte(us, true);
		initValue(model);
		if (master.getUserId() != null) {

			model.addAttribute(AppConstants.SUCCESS, AppConstants.UPDATE_SUCCESS);
		} else {
			model.addAttribute(AppConstants.ERROR, AppConstants.UPDATE_ERROR);
		}
		return "editCaseWorker";
	}
}