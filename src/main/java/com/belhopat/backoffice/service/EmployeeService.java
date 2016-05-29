package com.belhopat.backoffice.service;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.EmployeeDto;
import com.belhopat.backoffice.model.Employee;

@Service
public interface EmployeeService {

	public ResponseEntity<String> saveOrUpdateEmployee(EmployeeDto employeeDto);

	public DataTablesOutput<Employee> getEmployee(DataTablesInput input);

	public Employee getAnEmployee(Long id);

}
