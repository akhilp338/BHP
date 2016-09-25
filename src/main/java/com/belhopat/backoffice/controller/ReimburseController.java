package com.belhopat.backoffice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.model.Reimburse;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.EmployeeService;
import com.belhopat.backoffice.service.ReimburseService;

/**
 * @author Belhopat dev team Serves as a webservice handler class
 *
 */
@Controller
@RequestMapping("/api/reimburse")
public class ReimburseController {

	@Autowired
	BaseService baseService;

	@Autowired
	ReimburseService reimburseService;

	@Autowired
	EmployeeService employeeService;

	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateReimburse", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> saveOrUpdateCandidate(@RequestBody Reimburse reimburse) {
		return reimburseService.saveOrUpdateReimburse(reimburse);
	}

	@ResponseBody
	@RequestMapping(value = "/getReimburse", method = RequestMethod.POST)
	public ResponseEntity<Reimburse> saveOrUpdateCandidate(@RequestBody Long reimburseId) {
		return reimburseService.getReimburse(reimburseId);
	}

}
