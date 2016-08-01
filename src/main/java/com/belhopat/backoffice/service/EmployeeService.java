package com.belhopat.backoffice.service;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.EmployeeDto;
import com.belhopat.backoffice.model.Employee;

@Service
public interface EmployeeService {

	public ResponseEntity<Map<String, String>> saveOrUpdateEmployee(EmployeeDto employeeDto) throws MessagingException;

	public DataTablesOutput<Employee> getEmployee(DataTablesInput input);

	public EmployeeDto getAnEmployee(Long id);

}
