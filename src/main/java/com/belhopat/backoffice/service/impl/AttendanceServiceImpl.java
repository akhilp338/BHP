package com.belhopat.backoffice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.model.Attendance;
import com.belhopat.backoffice.repository.AttendanceRepository;
import com.belhopat.backoffice.service.AttendanceService;

/**
 * @author BHP_DEV service implementation for general functionalities
 */
@Component
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;

	@Override
	public UploadResponse saveAttendances(List<Attendance> attendances) {
		UploadResponse response = new UploadResponse();
		if (attendances != null && !attendances.isEmpty()) {
			attendanceRepository.save(attendances);
		}
		return response;
	}

	@Override
	public DataTablesOutput<Attendance> getAttendances(DataTablesInput input) {
		return attendanceRepository.findAll(input);
	}

}
