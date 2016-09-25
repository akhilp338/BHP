package com.belhopat.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.TaskDTO;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.ReimburseService;

/**
 * @author Belhopat dev team Serves as a webservice handler class
 *
 */
@Controller
@RequestMapping("/dashboard/api")
public class DashboardController {

	@Autowired
	BaseService baseService;

	@Autowired
	ReimburseService reimburseService;

	@ResponseBody
	@RequestMapping(value = "/approveOrRejectReimburse", method = RequestMethod.POST)
	public ResponseEntity<String> getStatesByCountry(@RequestBody TaskDTO task) {
		return reimburseService.approveOrRejectReimburseTask(task);
	}

}
