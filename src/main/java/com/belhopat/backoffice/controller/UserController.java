package com.belhopat.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.EmployeeDto;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.service.UserService;

/**
 * @author BHP_DEV
 * Handler for login user
 *
 */
@Controller
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public Employee saveUser(@RequestBody EmployeeDto employee) {
		return userService.createUser(employee);
	}
    

}