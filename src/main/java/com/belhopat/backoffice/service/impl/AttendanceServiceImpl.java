package com.belhopat.backoffice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.model.Attendance;
import com.belhopat.backoffice.repository.AttendanceRepository;
import com.belhopat.backoffice.repository.EmployeeRepository;
import com.belhopat.backoffice.service.AttendanceService;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.util.Constants;

/**
 * @author BHP_DEV service implementation for general functionalities
 */
@Component
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	BaseService baseService;

	@Override
	public UploadResponse saveAttendances(List<Attendance> attendances) {
		UploadResponse response = baseService.getSuccessResponse();
		response.setStatusMessage("Attendence File Uploaded Succesfully.");
		if (attendances != null && !attendances.isEmpty()) {
			attendanceRepository.save(attendances);
		}
		return response;
	}

	@Override
	public DataTablesOutput<Attendance> getAttendances(DataTablesInput input) {
		return attendanceRepository.findAll(input);
	}

	@Override
	public ResponseEntity<Map<String, List<?>>> getDropDownData() {
		Map<String, List<?>> dropDownMap = new HashMap<>();
		List<ResponseObject> employees = employeeRepository.getEmployeesDropDown();
		List<String> status = getStatusList();
		dropDownMap.put(Constants.EMPLOYEES, employees);
		dropDownMap.put(Constants.STATUS, status);
		return new ResponseEntity<Map<String, List<?>>>(dropDownMap, HttpStatus.OK);
	}

	private List<String> getStatusList() {
		List<String> status = new ArrayList<String>();
		status.add("P");
		status.add("WO-I");
		status.add("WO-II");
		status.add("P/WO-");
		status.add("A");
		return status;
	}

}
