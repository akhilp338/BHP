package com.belhopat.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.EmployeeDto;
import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.service.UserService;
import com.belhopat.backoffice.util.Constants;

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
	public  ResponseEntity <ResponseObject> saveUser(@RequestBody EmployeeDto employee) {
		if(userService.createUser(employee).getId() != null) {
			return new ResponseEntity<ResponseObject>(new ResponseObject(true, Constants.WEL_MAIL_SUCC),
					HttpStatus.OK);
		}else{
			return new ResponseEntity<ResponseObject>(new ResponseObject(resetStatus, Constants.WEL_MAIL_FAIL),
					HttpStatus.OK);
		}
	}
    

}