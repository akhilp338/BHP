package com.belhopat.backoffice.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.EmployeeDto;
import com.belhopat.backoffice.dto.EmployeeViewDTO;
import com.belhopat.backoffice.dto.UploadDTO;
import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.S3BucketFile;

@Service
public interface EmployeeService {

	public ResponseEntity<Map<String, String>> saveOrUpdateEmployee(EmployeeDto employeeDto) throws MessagingException;

	public DataTablesOutput<Employee> getEmployee(DataTablesInput input);

	public EmployeeDto getAnEmployee(Long id);

	public ResponseEntity<EmployeeViewDTO> getEmployeeView(Long id) throws ParseException;

	public ResponseEntity<Employee> getloggedInEmployee();

	public UploadResponse uploadFile(UploadDTO uploadDTO) throws Exception;

	public List<S3BucketFile> getFiles(Long employeeId) throws Exception;

}
