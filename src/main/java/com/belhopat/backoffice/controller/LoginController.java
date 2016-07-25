package com.belhopat.backoffice.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.dto.UserDTO;
import com.belhopat.backoffice.model.RoleTab;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.service.LoginService;
import com.belhopat.backoffice.service.UserService;
import com.belhopat.backoffice.util.Constants;

/**
 * @author BHP DEV TEAM Handler for login and logout functionality
 *
 */
@Controller
@RequestMapping("/*")
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	UserService userService;

	/**
	 * @return Loads the index page heart of the application
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getIndexPage() {
		return "index";
	}

	/**
	 * @param error
	 * @param logout
	 * @param request
	 * @return Handles the login and its validation
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			System.out.println("error");
			model.addObject("error", "Wrong User name or Password");
		}
		model.setViewName("/index");
		return model;
	}

	/**
	 * @return Once the log in is success , returns a view with user name
	 */
	@RequestMapping(value = "/loginSuccess")
	public ModelAndView loginSuccess() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			model.addObject("user", auth.getName());
		}
		model.setViewName("/index");
		return model;
	}

	/**
	 * @param request
	 * @throws ServletException
	 *             Log outs and kills the current session
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request) throws ServletException {
		request.getSession().invalidate();
		request.logout();
	}

	/**
	 * @param request
	 * @return username get the username for current logged in user
	 */
	@RequestMapping(value = "api/getUserName", method = RequestMethod.POST)
	public String getUserName(HttpServletRequest request) {
		return SecurityContextHolder.getContext().getAuthentication().getName().toString();
	}

	/**
	 * @param user
	 * @return
	 * @throws MessagingException
	 *             Generates a password and sends that password to users e mail
	 *             id
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> forgotPassword(@RequestBody User user) throws MessagingException, UnsupportedEncodingException {
		boolean userStatus = userService.generatePasswordResetLink(user.getEmail());
		if (userStatus)
			return new ResponseEntity<ResponseObject>(new ResponseObject(userStatus, Constants.PASS_RESET_SUCC_MSG),
					HttpStatus.OK);
		else
			return new ResponseEntity<ResponseObject>(new ResponseObject(userStatus, Constants.PASS_RESET_FAIL_MSG),
					HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "api/getUserTabs", method = RequestMethod.POST)
	public List<RoleTab> getUserTabs() {
		List<RoleTab> userTabs = loginService.getUserTabs();
		return userTabs;
	}

	/**
	 * @param user
	 * @return
	 * @throws MessagingException
	 *             Generates a password and sends that password to users e mail
	 *             id
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> resetPassword(@RequestBody UserDTO user) throws MessagingException {
		Boolean resetStatus = userService.resetPassword( user );
		if ( resetStatus )
			return new ResponseEntity<ResponseObject>(new ResponseObject(resetStatus, Constants.PASS_CHANGE_SUCC_MSG),
					HttpStatus.OK);
		else
			return new ResponseEntity<ResponseObject>(new ResponseObject(resetStatus, Constants.PASS_CHANGE_FAIL_MSG),
					HttpStatus.OK);
	}

}
