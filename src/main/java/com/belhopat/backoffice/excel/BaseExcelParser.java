package com.belhopat.backoffice.excel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.belhopat.backoffice.dto.UploadResponse;

public class BaseExcelParser {

	HSSFWorkbook workbook;

	protected Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}
		return null;
	}

	protected int getNoOfColumns() {
		Sheet sheet = workbook.getSheetAt(0);
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		return noOfColumns;
	}

	protected boolean containsDuplicates(List<String> list) {
		Set<String> set = new HashSet<String>(list);
		if (set.size() < list.size()) {
			return true;
		}
		return false;
	}

	protected String getDayCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case Cell.CELL_TYPE_BLANK:
			return "";
		default:
			return null;
		}
	}

	protected Double getDoubleCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return Double.valueOf(cell.getStringCellValue());
		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		default:
			return null;
		}
	}

	protected String getDayHeader(int cn) {
		switch (cn) {
		case 1:
			return "Today";
		case 2:
			return "Tomorrow";
		default:
			return " Day \n " + cn;
		}
	}

	protected void removeLastComma(String str) {
		if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
			str = str.substring(0, str.length() - 1);
		}
	}

	protected UploadResponse getSuccessResponse() {
		UploadResponse response = new UploadResponse();
		response.setStatus("success");
		response.setActionStatus(true);
		return response;
	}

	protected UploadResponse getErrorResponse() {
		UploadResponse response = new UploadResponse();
		response.setStatusMessage("Unexpected Error");
		response.setStatus("error");
		response.setActionStatus(false);
		return response;
	}
}
