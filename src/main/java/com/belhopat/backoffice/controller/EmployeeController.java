package com.belhopat.backoffice.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.EmployeeDto;
import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.EmployeeService;
/**
 * @author Belhopat dev team
 * Handler for all Employee related service calls
 */
@Controller
@RequestMapping("/api/employee")
public class EmployeeController {


	@Autowired
	BaseService baseService;

	@Autowired
	EmployeeService employeeService;
	
	/**
	 * @param employee
	 * @return response string
	 * Adds and edits the employee . Calls the service layer for persistence
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateEmployee", method = RequestMethod.POST)
	public ResponseEntity<String> saveOrUpdateEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.saveOrUpdateEmployee(employeeDto);
	}
	
	/**
	 * @param input
	 * @return employee list
	 * returns a list of employee entity for datatable population
	 */
	@ResponseBody
	@RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
	public DataTablesOutput<Employee> getEmployee(@Valid DataTablesInput input) {
		return employeeService.getEmployee(input);
	}
	
	/**
	 * @param requestObject
	 * @return employeeEntity
	 * gets an employee entity for edit functionality
	 */
	@ResponseBody
	@RequestMapping(value = "/getAnEmployee", method = RequestMethod.POST)
	public Employee getAnEmployee(@RequestBody RequestObject requestObject) {
		return employeeService.getAnEmployee(requestObject.getId());
	}
	@ResponseBody
	@RequestMapping(value = "/getEmployeeDropdowns", method = RequestMethod.POST)
	public Map<String, List<?>> getEmployeeDropdowns() {
		return baseService.getEmployeeDropdowns();
	}
	

}
