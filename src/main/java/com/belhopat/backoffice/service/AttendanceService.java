package com.belhopat.backoffice.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.model.Attendance;

@Service
public interface AttendanceService {

	public UploadResponse saveAttendances(List<Attendance> attendances);

	public DataTablesOutput<Attendance> getAttendances(DataTablesInput input);
}
