package com.belhopat.backoffice.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.belhopat.backoffice.model.Attendance;
import com.belhopat.backoffice.model.Employee;

public class PoiReadExcelFile {
    public static void main( String[] args ) throws ParseException {
        try {
            FileInputStream fileInputStream =
                new FileInputStream( "/home/sujith/Desktop/2015_Sep.xls" );
            HSSFWorkbook workbook = new HSSFWorkbook( fileInputStream );
            // int month = getMonthIndex( "Mar" );
            getEmployeeCodes( workbook );
            List< Attendance > attendances = getAttendances( workbook );
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private static List< String > getEmployeeCodes( HSSFWorkbook workbook ) {
        List< String > codes = new ArrayList< >();
        Sheet sheet = workbook.getSheetAt( 0 );
        int rowNum = 4;
        while ( rowNum < sheet.getLastRowNum() ) {
            Row row = sheet.getRow( rowNum );
            Cell employeeCodeCell = row.getCell( 0 );
            boolean isCompleted = isParsingCompleted( employeeCodeCell );
            if ( isCompleted ) {
                break;
            }
            String code = getEmployeeCode( employeeCodeCell );
            if ( code == null ) {
                break;
            }
            codes.add( code );
            rowNum = rowNum + 6;
        }
        return codes;
    }

    private static List< Attendance > getAttendances( HSSFWorkbook workbook ) throws ParseException {
        List< Attendance > attendances = new ArrayList< >();
        Sheet sheet = workbook.getSheetAt( 0 );
        Date date = getAttendanceMonth( sheet );
        int rowNum = 4;
        while ( rowNum < sheet.getLastRowNum() ) {
            Row row = sheet.getRow( rowNum );
            Cell employeeCodeCell = row.getCell( 0 );
            boolean isCompleted = isParsingCompleted( employeeCodeCell );
            if ( isCompleted ) {
                return attendances;
            }
            else if ( employeeCodeCell.getCellType() == Cell.CELL_TYPE_STRING ) {
                Row attendanceRow = sheet.getRow( rowNum + 4 );
                for ( int cellNum = 1; cellNum <= 30; cellNum++ ) {
                    Attendance attendance = new Attendance();
                    Cell cell = attendanceRow.getCell( cellNum );
                    String value = cell.getStringCellValue();
                    String lines[] = value.split( "\\r?\\n" );
                    setEmployee( employeeCodeCell, attendance );
                    attendance.setDate( setDayOfMonth( date, cellNum ) );
                    attendance.setInTime( lines[ 0 ] );
                    attendance.setOutTime( lines[ 1 ] );
                    attendance.setLateMinutes( Integer.valueOf( lines[ 2 ] ) );
                    attendance.setEarlyMinutes( Integer.valueOf( lines[ 3 ] ) );
                    attendance.setWorkHours( lines[ 4 ] );
                    attendance.setStatus( lines[ 5 ] );
                    attendances.add( attendance );
                }
            }
            rowNum = rowNum + 6;
        }
        return attendances;
    }

    private static Date getAttendanceMonth( Sheet sheet ) throws ParseException {
        Row row = sheet.getRow( 1 );
        Cell cell = row.getCell( 0 );
        String value = cell.getStringCellValue();
        String attendanceMonthAndYear = value.split( ":" )[ 1 ];
        String monthString = attendanceMonthAndYear.split( "-" )[ 0 ].trim();
        String yearString = attendanceMonthAndYear.split( "-" )[ 1 ].trim();
        int month = getMonthIndex( monthString );
        int year = Integer.valueOf( yearString );
        Date date = getDate( month, year );
        return date;
    }

    private static void setAttendanceDetails( Row row, Attendance attendance ) {
        for ( int cellNum = 1; cellNum <= 30; cellNum++ ) {
            Date date = getDate( 9, 2016 );
            Cell cell = row.getCell( cellNum );
            String value = cell.getStringCellValue();
            String lines[] = value.split( "\\r?\\n" );
            attendance.setDate( setDayOfMonth( date, cellNum ) );
            attendance.setInTime( lines[ 0 ] );
            attendance.setOutTime( lines[ 1 ] );
            attendance.setLateMinutes( Integer.valueOf( lines[ 2 ] ) );
            attendance.setEarlyMinutes( Integer.valueOf( lines[ 3 ] ) );
            attendance.setWorkHours( lines[ 4 ] );
            attendance.setStatus( lines[ 5 ] );
        }
    }

    private static long getHoursMinutesToMilliseconds( String string ) {
        String hoursString = string.split( ":" )[ 0 ];
        String minutesString = string.split( ":" )[ 1 ];
        int hours = Integer.valueOf( hoursString );
        int minutes = Integer.valueOf( minutesString );
        int milliSec = hours * 3600000 + minutes * 60000;
        return milliSec;
    }

    private static void setEmployee( Cell employeeCodeCell, Attendance attendance ) {
        Map< String, Long > employeeMap = getMap();
        String code = getEmployeeCode( employeeCodeCell );
        if ( code != null ) {
            attendance.setEmployee( new Employee( employeeMap.get( code ) ) );
        }
    }

    private static String getEmployeeCode( Cell employeeCodeCell ) {
        String code = null;
        if ( employeeCodeCell.getCellType() == Cell.CELL_TYPE_STRING ) {
            String value = employeeCodeCell.getStringCellValue();
            code = value.split( ":" )[ 1 ].trim();
            System.out.println( "code " + code );
        }
        return code;
    }

    private static Map< String, Long > getMap() {
        Map< String, Long > employeeMap = new HashMap< >();
        employeeMap.put( "C1404001", 1L );
        employeeMap.put( "C1407001", 1L );
        employeeMap.put( "C1411003", 1L );
        employeeMap.put( "C1411004", 1L );
        employeeMap.put( "C1501001", 1L );
        employeeMap.put( "C1503001", 1L );
        employeeMap.put( "C1504001", 1L );
        employeeMap.put( "C1505005", 1L );
        return employeeMap;
    }

    private static boolean isParsingCompleted( Cell employeeCodeCell ) {
        if ( employeeCodeCell == null
            || employeeCodeCell.getCellType() == Cell.CELL_TYPE_BLANK ) {
            return true;
        }
        return false;
    }

    public static Date getDate( int month, int year ) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set( Calendar.MONTH, month );
        calendar.set( Calendar.YEAR, year );
        Date date = calendar.getTime();
        return date;
    }

    public static Date setDayOfMonth( Date date, int day ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        calendar.set( Calendar.DAY_OF_MONTH, day );
        return calendar.getTime();
    }

    public static int getMonthIndex( String monthName ) throws ParseException {
        Date date = new SimpleDateFormat( "MMMM" ).parse( monthName );
        Calendar cal = Calendar.getInstance();
        cal.setTime( date );
        return cal.get( Calendar.MONTH );
    }
}
