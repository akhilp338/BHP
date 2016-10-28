package com.belhopat.backoffice.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.model.Expense;
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
	public ResponseEntity<Map<String, String>> saveOrUpdateCandidate(@RequestBody List<Expense> expences) {
		return reimburseService.saveOrUpdateReimburse(expences);
	}

	@ResponseBody
	@RequestMapping(value = "/getReimburse", method = RequestMethod.GET)
	public ResponseEntity<Reimburse> getReimburse(@RequestBody Long reimburseId) {
		return reimburseService.getReimburse(reimburseId);
	}

	@ResponseBody
	@RequestMapping(value = "/uploadReimburseFile", method = RequestMethod.POST)
	public UploadResponse uploadReimburseFile(@RequestParam("file") MultipartFile file, @RequestParam Long reimburseId)
			throws IllegalStateException, Exception {
		UploadResponse response = reimburseService.uploadReimburseFile(file, reimburseId);
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/getDropDownData", method = RequestMethod.GET)

	public ResponseEntity<Map<String, List<?>>> getDropDownData() {
		return reimburseService.getDropDownData();
	}

}
