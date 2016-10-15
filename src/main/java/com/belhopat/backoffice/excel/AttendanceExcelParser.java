package com.belhopat.backoffice.excel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.model.Attendance;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.AttendanceRepository;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.DateUtil;

public class AttendanceExcelParser extends BaseExcelParser {

	AttendanceRepository attendanceRepository;
	Map<String, Long> employeeMap;

	public AttendanceExcelParser(HSSFWorkbook workBook, AttendanceRepository attendanceRepository,
			Map<String, Long> employeeMap) {
		this.workbook = workBook;
		this.attendanceRepository = attendanceRepository;
		this.employeeMap = employeeMap;
	}

	public UploadResponse getParsedData() throws ParseException {
		UploadResponse response = getErrorResponse();
		List<String> employeeCodes = getEmployeeCodes();
		if (employeeCodes.isEmpty()) {
			response.setStatusMessage("File contains wrong data. Please retry");
			return response;
		}
		boolean containsDuplicates = containsDuplicates(employeeCodes);
		if (containsDuplicates) {
			response.setStatusMessage("Duplicate employee codes exists");
			return response;
		}
		boolean isDataAlreadyExistForMonth = isDataAlreadyExistForMonth();
		if (isDataAlreadyExistForMonth) {
			response.setStatusMessage("File for this month is already uploaded");
			return response;
		}
		List<Attendance> attendances = getAttendances();
		if (attendances.isEmpty()) {
			response.setStatusMessage("File contains wrong data. Please retry");
			return response;
		}
		boolean isFileContailsPartialData = isFileContailsPartialData();
		if (isFileContailsPartialData) {
			response.setStatusMessage("File contains partial data. Please retry");
			return response;
		}
		response = getSuccessResponse();
		response.setList(attendances);
		return response;
	}

	private boolean isFileContailsPartialData() {
		return false;
	}

	private List<String> getEmployeeCodes() {
		List<String> codes = new ArrayList<>();
		Sheet sheet = workbook.getSheetAt(0);
		int rowNum = 4;
		while (rowNum < sheet.getLastRowNum()) {
			Row row = sheet.getRow(rowNum);
			Cell employeeCodeCell = row.getCell(0);
			boolean isCompleted = isParsingCompleted(employeeCodeCell);
			if (isCompleted) {
				break;
			}
			String code = getEmployeeCode(employeeCodeCell);
			if (code == null) {
				break;
			}
			codes.add(code);
			rowNum = rowNum + 6;
		}
		return codes;
	}

	private List<Attendance> getAttendances() throws ParseException {
		List<Attendance> attendances = new ArrayList<>();
		Sheet sheet = workbook.getSheetAt(0);
		Date date = getAttendanceMonth(sheet);
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		int rowNum = 4;
		while (rowNum < sheet.getLastRowNum()) {
			Row row = sheet.getRow(rowNum);
			Cell employeeCodeCell = row.getCell(0);
			boolean isCompleted = isParsingCompleted(employeeCodeCell);
			if (isCompleted) {
				return attendances;
			} else if (employeeCodeCell.getCellType() == Cell.CELL_TYPE_STRING) {
				Row attendanceRow = sheet.getRow(rowNum + 4);
				for (int cellNum = 1; cellNum <= 30; cellNum++) {
					Attendance attendance = new Attendance();
					Cell cell = attendanceRow.getCell(cellNum);
					String value = cell.getStringCellValue();
					String lines[] = value.split("\\r?\\n");
					setEmployee(employeeCodeCell, attendance);
					attendance.setDate(DateUtil.setDayOfMonth(date, cellNum));
					attendance.setInTime(lines[0]);
					attendance.setOutTime(lines[1]);
					attendance.setLateMinutes(Integer.valueOf(lines[2]));
					attendance.setEarlyMinutes(Integer.valueOf(lines[3]));
					attendance.setWorkHours(lines[4]);
					attendance.setStatus(lines[5]);
					attendance.setBaseAttributes(loggedInUser);
					attendances.add(attendance);
				}
			}
			rowNum = rowNum + 6;
		}
		return attendances;
	}

	private Date getAttendanceMonth(Sheet sheet) throws ParseException {
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		String value = cell.getStringCellValue();
		String attendanceMonthAndYear = value.split(":")[1];
		String monthString = attendanceMonthAndYear.split("-")[0].trim();
		String yearString = attendanceMonthAndYear.split("-")[1].trim();
		int month = DateUtil.getMonthIndex(monthString);
		int year = Integer.valueOf(yearString);
		Date date = DateUtil.getDate(month, year);
		return date;
	}

	private void setEmployee(Cell employeeCodeCell, Attendance attendance) {
		String code = getEmployeeCode(employeeCodeCell);
		if (code != null) {
			attendance.setEmployee(new Employee(employeeMap.get(code)));
		}
	}

	private String getEmployeeCode(Cell employeeCodeCell) {
		String code = null;
		if (employeeCodeCell.getCellType() == Cell.CELL_TYPE_STRING) {
			String value = employeeCodeCell.getStringCellValue();
			code = value.split(":")[1].trim();
		}
		return code;
	}

	private boolean isParsingCompleted(Cell employeeCodeCell) {
		if (employeeCodeCell == null || employeeCodeCell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return true;
		}
		return false;
	}

	private boolean isDataAlreadyExistForMonth() throws ParseException {
		Sheet sheet = workbook.getSheetAt(0);
		Date date = getAttendanceMonth(sheet);
		Date startDate = DateUtil.getStartDateOfMonth(date);
		Date endDate = DateUtil.getEndDateOfMonth(date);
		Integer attendanceCountByMonth = attendanceRepository.findCountByDate(startDate, endDate);
		if (attendanceCountByMonth > 0) {
			return true;
		}
		return false;
	}
}
