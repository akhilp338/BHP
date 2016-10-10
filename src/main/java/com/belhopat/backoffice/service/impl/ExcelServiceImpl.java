package com.belhopat.backoffice.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
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
import com.belhopat.backoffice.util.DateUtil;

@Component
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    BaseService baseService;

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public UploadResponse uploadExcel( String type, MultipartFile file ) throws IOException {
        UploadResponse response = new UploadResponse();
        response = validateExcelFile( file );
        if ( !response.isActionStatus() ) {
            return response;
        }
        HSSFWorkbook workBook = getHSSFWorkbook( file );
        if ( workBook == null ) {
            response = getErrorResponse();
            return response;
        }
        switch ( type ) {
            case "LL":
                response = uploadLiquidityLimitsExcel( workBook );
                break;
            case "CPL":
                response = uploadLiquidityLimitsExcel( workBook );
                break;
            case "CCL":
                response = uploadLiquidityLimitsExcel( workBook );
                break;
            case "GP":
                response = uploadLiquidityLimitsExcel( workBook );
                break;
            case "CRM":
                response = uploadLiquidityLimitsExcel( workBook );
                break;
        }
        return response;
    }

    private HSSFWorkbook getHSSFWorkbook( MultipartFile file ) throws IOException {
        HSSFWorkbook workBook = new HSSFWorkbook( new ByteArrayInputStream( file.getBytes() ) );
        return workBook;

    }

    private UploadResponse validateExcelFile( MultipartFile multipartFile ) throws IOException {
        UploadResponse response = getErrorResponse();
        if ( !multipartFile.isEmpty() ) {
            if ( multipartFile.getOriginalFilename().endsWith( "XLS" ) ) {
                response = getSuccessResponse();
            }
            else {
                response.setStatusMessage( "Select XLS File" );
            }
        }
        else {
            response.setStatusMessage( "Selected File is Empty" );
        }
        return response;
    }

    @SuppressWarnings( "unchecked" )
    private UploadResponse uploadLiquidityLimitsExcel( HSSFWorkbook workBook ) throws IOException {
        UploadResponse response = getErrorResponse();
        Map< String, Long > employeeMap = baseService.getEmployeeIdAndCodeMap();
        int year = 2016;
        int month = 9;
        Date date = DateUtil.getDate( month, year );
        try {
            AttendanceExcelParser excelParser =
                new AttendanceExcelParser( workBook, attendanceRepository, employeeMap, date );
            response = excelParser.getParsedData();
            if ( response.isActionStatus() ) {
                List< Attendance > attendances = ( List< Attendance > ) response.getList();
                response = attendanceService.saveAttendances( attendances );
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return response;
    }

    private UploadResponse getSuccessResponse() {
        UploadResponse response = new UploadResponse();
        response.setStatus( "success" );
        response.setActionStatus( true );
        return response;
    }

    private UploadResponse getErrorResponse() {
        UploadResponse response = new UploadResponse();
        response.setStatusMessage( "Unexpected Error" );
        response.setStatus( "error" );
        response.setActionStatus( false );
        return response;
    }

}
