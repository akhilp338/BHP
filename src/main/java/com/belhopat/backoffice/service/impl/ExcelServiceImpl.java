package com.belhopat.backoffice.service.impl;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.excel.AttendanceExcelParser;
import com.belhopat.backoffice.model.Attendance;
import com.belhopat.backoffice.repository.AttendanceRepository;
import com.belhopat.backoffice.service.AttendanceService;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.ExcelService;

@Component
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	BaseService baseService;

	@Autowired
	AttendanceService attendanceService;

	@Autowired
	AttendanceRepository attendanceRepository;

	@Override
	public UploadResponse uploadExcel(String type, MultipartFile file) throws IOException {
		UploadResponse response = new UploadResponse();
		response = validateExcelFile(file);
		// if (!response.isActionStatus()) {
		// return response;
		// }
		// HSSFWorkbook workBook = getHSSFWorkbook(file);
		// if (workBook == null) {
		// response = getErrorResponse();
		// return response;
		// }
		HSSFWorkbook workBook = null;
		switch (type) {
		case "ATNDNCE":
			response = uploadAttendanceExcel(workBook);
			break;
		case "CPL":
			response = uploadAttendanceExcel(workBook);
			break;
		case "CCL":
			response = uploadAttendanceExcel(workBook);
			break;
		case "GP":
			response = uploadAttendanceExcel(workBook);
			break;
		case "CRM":
			response = uploadAttendanceExcel(workBook);
			break;
		}
		return response;
	}

	private HSSFWorkbook getHSSFWorkbook(MultipartFile file) throws IOException {
		HSSFWorkbook workBook = new HSSFWorkbook(new ByteArrayInputStream(file.getBytes()));
		return workBook;

	}

	private UploadResponse validateExcelFile(MultipartFile multipartFile) throws IOException {
		UploadResponse response = baseService.getErrorResponse();
		if (multipartFile != null && !multipartFile.isEmpty()) {
			if (multipartFile.getOriginalFilename().endsWith("XLS")) {
				response = baseService.getSuccessResponse();
			} else {
				response.setStatusMessage("Select XLS File");
			}
		} else {
			response.setStatusMessage("Selected File is Empty");
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	private UploadResponse uploadAttendanceExcel(HSSFWorkbook workBook) throws IOException {
		FileInputStream fileInputStream = new FileInputStream("/home/sujith/Desktop/2015_Sep.xls");
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		UploadResponse response = baseService.getErrorResponse();
		Map<String, Long> employeeMap = getMap();
		try {
			AttendanceExcelParser excelParser = new AttendanceExcelParser(workbook, attendanceRepository, employeeMap);
			response = excelParser.getParsedData();
			if (response.isActionStatus()) {
				List<Attendance> attendances = (List<Attendance>) response.getList();
				response = attendanceService.saveAttendances(attendances);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private Map<String, Long> getMap() {
		Map<String, Long> employeeMap = new HashMap<>();
		employeeMap.put("C1404001", 1L);
		employeeMap.put("C1407001", 2L);
		employeeMap.put("C1411003", 3L);
		employeeMap.put("C1411004", 1L);
		employeeMap.put("C1501001", 5L);
		employeeMap.put("C1503001", 6L);
		employeeMap.put("C1504001", 1L);
		employeeMap.put("C1505005", 2L);
		return employeeMap;
	}
}
