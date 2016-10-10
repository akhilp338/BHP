package com.belhopat.backoffice.excel;

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
import com.belhopat.backoffice.repository.AttendanceRepository;

public class AttendanceExcelParser extends BaseExcelParser {

    AttendanceRepository attendanceRepository;
    Map< String, Long > employeeMap;
    Date date;

    public AttendanceExcelParser( HSSFWorkbook workBook,
        AttendanceRepository attendanceRepository, Map< String, Long > employeeMap, Date date ) {
        this.workbook = workBook;
        this.attendanceRepository = attendanceRepository;
        this.employeeMap = employeeMap;
        this.date = date;
    }

    public UploadResponse getParsedData() {
        UploadResponse response = getErrorResponse();
        List< String > employeeCodes = getEmployeeCodes();
        boolean containsDuplicates = containsDuplicates( employeeCodes );
        if ( containsDuplicates ) {
            response.setStatusMessage( "as" );
            return response;
        }
        boolean isNoOfColumsValid = isNoOfColumsValid();
        if ( !isNoOfColumsValid ) {
            response.setStatusMessage( "sda" );
            return response;
        }
        List< Attendance > attendances = attendanceRepository.findAll();
        // response = getLiquiditySourcesLimits(days, liquiditySourcesLimits);
        return response;
    }

    // private UploadResponse getLiquiditySourcesLimits(int days,
    // List<LiquiditySourcesLimitViewEntity> liquiditySourcesLimits) {
    // List<LiquiditySourcesLimitViewEntity> lsLimits = new ArrayList<>();
    // UploadResponse response = getErrorResponse();
    // Sheet firstSheet = workbook.getSheetAt(0);
    // for (Row row : firstSheet) {
    // if (row != null && row.getRowNum() > 0) {
    // LiquiditySourcesLimitViewEntity lsLimit = null;
    // lsLimit = getLiquiditySourcesLimit(row, liquiditySourcesLimits);
    // if (lsLimit == null) {
    // response.setStatusMessage(Constants.WRONG_DATA_TYPE_ACNT_CODE);
    // return response;
    // }
    // response = setAvailabilityLimit(row, lsLimit);
    // if (!response.isActionStatus()) {
    // return response;
    // }
    // response = setDayNumbers(row, lsLimit, days);
    // if (!response.isActionStatus()) {
    // return response;
    // }
    // lsLimits.add(lsLimit);
    // }
    // if (!lsLimits.isEmpty()) {
    // response = getSuccessResponse();
    // response.setList(lsLimits);
    // }
    // }
    // return response;
    // }

    private List< String > getEmployeeCodes() {
        Sheet sheet = workbook.getSheetAt( 0 );
        int rowNum = 1;
        while ( rowNum < sheet.getLastRowNum() ) {

        }
        return null;
    }

    private boolean isNoOfDaysFieldValid( int days ) {
        Sheet sheet = workbook.getSheetAt( 0 );
        double value = 0;
        for ( int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++ ) {
            Row row = sheet.getRow( rowNum );
            if ( row != null ) {
                Cell cell = row.getCell( 3 );
                if ( cell != null ) {
                    if ( cell.getCellType() == Cell.CELL_TYPE_NUMERIC ) {
                        value = cell.getNumericCellValue();
                    }
                    else if ( cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                        String stringValue = cell.getStringCellValue();
                        value = Double.valueOf( stringValue );
                    }
                }
                if ( value > 0 && value < days ) {
                    break;
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNoOfColumsValid() {
        int noOfColumns = getNoOfColumns();
        if ( noOfColumns == 4 ) {
            return true;
        }
        return false;
    }

    // private LiquiditySourcesLimitViewEntity getLiquiditySourcesLimit(Row row,
    // List<LiquiditySourcesLimitViewEntity> liquiditySourcesLimits) {
    // if (row != null) {
    // UserEntity loggedInUser = SessionManager.getCurrentUserAsEntity();
    // Cell cell = row.getCell(0);
    // if (cell != null) {
    // String sourceName = null;
    // if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
    // double value = cell.getNumericCellValue();
    // sourceName = String.valueOf(value).split("\\.")[0];
    // } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
    // sourceName = cell.getStringCellValue();
    // }
    // if (sourceName != null) {
    // for (LiquiditySourcesLimitViewEntity lsLimit : liquiditySourcesLimits) {
    // if (lsLimit.getSourceName().equals(sourceName)
    // && lsLimit.getInstance().getId().equals(loggedInUser.getInstance().getId())) {
    // return lsLimit;
    // }
    // }
    // return getNewLiquiditySourcesLimit(sourceName);
    // }
    // }
    // }
    // return null;
    // }
    //
    // private LiquiditySourcesLimitViewEntity getNewLiquiditySourcesLimit(String sourceName) {
    // LiquiditySourcesLimitViewEntity lsLimit = new LiquiditySourcesLimitViewEntity();
    // lsLimit.setSourceName(sourceName);
    // return lsLimit;
    // }
    //
    // private UploadResponse setAvailabilityLimit(Row row, LiquiditySourcesLimitViewEntity lsLimit)
    // {
    // UploadResponse response = getErrorResponse();
    // if (row != null) {
    // Cell cell = row.getCell(2);
    // if (cell != null) {
    // Double value = null;
    // if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
    // value = cell.getNumericCellValue();
    // } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
    // String stringValue = cell.getStringCellValue();
    // value = Double.valueOf(stringValue);
    // } else {
    // response.setStatusMessage(Constants.WRONG_DATA_TYPE_NOT_CAP);
    // return response;
    // }
    // lsLimit.setAvailabilityLimit(BigDecimal.valueOf(value));
    // response.setActionStatus(true);
    // }
    // }
    // return response;
    // }
    //
    // private UploadResponse setDayNumbers(Row row, LiquiditySourcesLimitViewEntity lsLimit, int
    // days) {
    // UploadResponse response = getErrorResponse();
    // Double noOfDays = null;
    // if (row != null) {
    // String dayNumbers = "";
    // Cell cell = row.getCell(3);
    // if (cell != null) {
    // noOfDays = getDoubleCellValue(cell);
    // if (!(noOfDays != null && noOfDays > 0)) {
    // return response;
    // }
    // }
    // for (int cellNum = 0; cellNum < noOfDays; cellNum++) {
    // dayNumbers = dayNumbers.isEmpty() ? dayNumbers : dayNumbers + ",";
    // dayNumbers = dayNumbers + String.valueOf(cellNum);
    // }
    // lsLimit.setDayNumbers(dayNumbers);
    // response.setActionStatus(true);
    // }
    // return response;
    // }

    private List< String > getFundingSources() {
        Sheet sheet = workbook.getSheetAt( 0 );
        List< String > fundingSources = new ArrayList< >();
        for ( int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++ ) {
            Row row = sheet.getRow( rowNum );
            if ( row != null ) {
                Cell cell = row.getCell( 0 );
                if ( cell != null ) {
                    if ( cell.getCellType() == Cell.CELL_TYPE_NUMERIC ) {
                        double value = cell.getNumericCellValue();
                        String stringValue = String.valueOf( value ).split( "\\." )[ 0 ];
                        fundingSources.add( stringValue );
                    }
                    else if ( cell.getCellType() == Cell.CELL_TYPE_STRING ) {
                        fundingSources.add( cell.getStringCellValue() );
                    }
                }
            }
        }
        return fundingSources;
    }
}
