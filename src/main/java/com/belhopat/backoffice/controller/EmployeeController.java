package com.belhopat.backoffice.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.EmployeeDto;
import com.belhopat.backoffice.dto.EmployeeViewDTO;
import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.dto.UploadDTO;
import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.S3BucketFile;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.EmployeeService;
import com.belhopat.backoffice.service.S3BucketService;

/**
 * @author Belhopat dev team Handler for all Employee related service calls
 */
@Controller
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	BaseService baseService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	S3BucketService S3BucketService;

	/**
	 * @param employee
	 * @return response string Adds and edits the employee . Calls the service
	 *         layer for persistence
	 * @throws MessagingException
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateEmployee", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> saveOrUpdateEmployee(@RequestBody EmployeeDto employeeDto)
			throws MessagingException {
		return employeeService.saveOrUpdateEmployee(employeeDto);
	}

	/**
	 * @param input
	 * @return employee list returns a list of employee entity for datatable
	 *         population
	 */
	@ResponseBody
	@RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
	public DataTablesOutput<Employee> getEmployee(@Valid DataTablesInput input) {
		return employeeService.getEmployee(input);
	}

	/**
	 * @param requestObject
	 * @return employeeEntity gets an employee entity for edit functionality
	 */
	@ResponseBody
	@RequestMapping(value = "/getAnEmployee", method = RequestMethod.POST)
	public EmployeeDto getAnEmployee(@RequestBody RequestObject requestObject) {
		return employeeService.getAnEmployee(requestObject.getId());
	}

	@ResponseBody
	@RequestMapping(value = "/getloggedInEmployee", method = RequestMethod.GET)
	public ResponseEntity<Employee> getReimburseEmployee() {
		return employeeService.getloggedInEmployee();
	}

	/**
	 * @param requestObject
	 * @return Candidate For edit candidate , gets the id and fetches the
	 *         candidate from database
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getEmployeeView", method = RequestMethod.POST)

	public ResponseEntity<EmployeeViewDTO> getEmployeeView(@RequestBody RequestObject requestObject)
			throws ParseException {
		return employeeService.getEmployeeView(requestObject.getId());
	}

	/**
	 * @param requestObject
	 * @return employeeEntity gets dropdown data
	 */
	@ResponseBody
	@RequestMapping(value = "/getDropDownData", method = RequestMethod.GET)
	public Map<String, List<?>> getEmployeeDropdowns() {
		return baseService.getEmployeeDropdowns();
	}

	@ResponseBody
	@RequestMapping("/uploadFile")
	public UploadResponse uploadResources(@ModelAttribute UploadDTO uploadDTO) throws IOException, Exception {
		UploadResponse response = employeeService.uploadFile(uploadDTO);
		return response;
	}

	@ResponseBody
	@RequestMapping("/getS3Files")
	public List<S3BucketFile> getS3Files(@RequestParam Long candidateId) throws IOException, Exception {
		List<S3BucketFile> s3Files = employeeService.getFiles(candidateId);
		return s3Files;
	}

	@ResponseBody
	@RequestMapping("/getS3File")
	public void getS3File(@RequestParam Long S3BucketFileId, HttpServletResponse response)
			throws IOException, Exception {
		S3BucketService.downloadFile(S3BucketFileId, response);
	}

}
