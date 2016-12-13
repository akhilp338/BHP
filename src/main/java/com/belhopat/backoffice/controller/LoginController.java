package com.belhopat.backoffice.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.belhopat.backoffice.model.ModuleTab;
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) {
			return "/dashboard";
		}
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
		SecurityContextHolder.clearContext();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		loginService.logout(request);
	}

	@RequestMapping(value = "/forceLogout", method = RequestMethod.GET)
	public void forceLogout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws ServletException, IOException {
		if (authentication != null && authentication.getDetails() != null) {
			try {
				httpServletRequest.getSession().invalidate();
				SecurityContextHolder.clearContext();
				httpServletRequest.logout();
				System.out.println("User Successfully Logout");
				// you can add more codes here when the user successfully logs
				// out,
				// such as updating the database for last active.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		// redirect to login
		httpServletResponse.sendRedirect("/BelhopatBackOffice");
	}

	@RequestMapping(value = "/sessioncheck", method = RequestMethod.GET)
	public String sessionCheck(HttpServletRequest request) throws ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			return auth.getName();
		}
		return null;
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
	public ResponseEntity<ResponseObject> forgotPassword(@RequestBody User user)
			throws MessagingException, UnsupportedEncodingException {
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
	public List<ModuleTab> getUserTabs() {
		List<ModuleTab> userTabs = loginService.getUserTabs();
		return userTabs;
	}

	/**
	 * @param user
	 * @return
	 * @throws MessagingException
	 *             Generates a password and sends that password to users e mail
	 *             id
	 * @throws ServletException
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> resetPassword(@RequestBody UserDTO user, HttpServletRequest request)
			throws MessagingException, ServletException {
		Boolean resetStatus = userService.resetPassword(user);
		if (resetStatus) {
			loginService.logout(request);
			return new ResponseEntity<ResponseObject>(new ResponseObject(resetStatus, Constants.PASS_CHANGE_SUCC_MSG),
					HttpStatus.OK);
		} else
			return new ResponseEntity<ResponseObject>(new ResponseObject(resetStatus, Constants.PASS_CHANGE_FAIL_MSG),
					HttpStatus.OK);
	}

}
