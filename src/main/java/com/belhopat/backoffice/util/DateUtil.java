package com.belhopat.backoffice.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.velocity.tools.generic.DateTool;

/**
 * @author BHP_DEV A util file for date format conversion
 *
 */
public class DateUtil extends DateTool {

	public static String getCurrentDateInGivenFormat(String givenDateFormat) throws ParseException {
		SimpleDateFormat localDateFormat = new SimpleDateFormat(givenDateFormat);
		return localDateFormat.format(new Date());
	}

	public static Timestamp parseDate(String dateString) throws ParseException {
		Timestamp ts = Timestamp.valueOf(dateString);
		return ts;
	}

	public static Date toDdMmYyyyHhMmSs(String dateString) throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.DD_MM_YYYY_HH_MM_SS);
		Date date = null;
		date = format.parse(dateString);
		return date;
	}

	public static String toDdMmYyyyHhMmSs(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.DD_MM_YYYY_HH_MM_SS);
		format.setLenient(false);
		String dateString = null;
		dateString = format.format(date);
		return dateString;
	}

	public static String toMMMMddYYYY(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.MMMM_DD_YYYY);
		format.setLenient(false);
		String dateString = null;
		dateString = format.format(date);
		return dateString;
	}

	public static Date dateStringToDdMmYyyyDate(String dateString) throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.DD_MM_YYYY);
		format.setLenient(false);
		Date date = null;
		date = format.parse(dateString);
		return date;
	}

	public static String toDdMmYyyy(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.DD_MM_YYYY);
		format.setLenient(false);
		String dateString = null;
		dateString = format.format(date);
		return dateString;
	}

	public static Date toDdMmYyyyHhMm(String dateString) throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.DD_MM_YYYY_HH_MM);
		format.setLenient(false);
		Date date = null;
		date = format.parse(dateString);
		return date;
	}

	public static String toDdMmYyyyHhMm(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.DD_MM_YYYY_HH_MM);
		format.setLenient(false);
		String dateString = null;
		dateString = format.format(date);
		return dateString;
	}

	public static String toMmmmDdYyyy(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.MMMM_DD_YYYY);
		String dateString = null;
		dateString = format.format(date);
		return dateString;
	}

	public static Date toMmmmDdYyyy(String dateString) throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.MMMM_DD_YYYY);
		format.setLenient(false);
		Date date = null;
		date = format.parse(dateString);
		return date;
	}

	public static String toMmmmDdYyyyHhMm(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.MMMM_DD_YYYY_HH_MM);
		String dateString = null;
		dateString = format.format(date);
		return dateString;
	}

	public static Date dateToDdMmYyyyDate(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.DD_MM_YYYY);
		String dateString = null;
		dateString = format.format(date);
		Date dateWithoutTime = format.parse(dateString);
		return dateWithoutTime;
	}

	public static String getCurrentDateInDdMmYyyy() throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.DD_MM_YYYY);
		String dateString = null;
		format.setLenient(false);
		dateString = format.format(new Date());
		return dateString;
	}

	public static String getCurrentDateInMmmmDdYyyyString() throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.MMMM_DD_YYYY);
		String dateString = null;
		format.setLenient(false);
		dateString = format.format(new Date());
		return dateString;
	}

	public static String getDateBeforeAMonthAsString() throws ParseException {
		DateFormat format = new SimpleDateFormat(Constants.DD_MM_YYYY);
		Calendar c = Calendar.getInstance();
		Date currentDate = new Date();
		String dateString = null;
		format.setLenient(false);
		c.setTime(currentDate);
		c.add(Calendar.DATE, -30);
		dateString = format.format(c.getTime());
		return dateString;
	}

	/**
	 * add days to date in java
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, Integer days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * subtract days to date in java
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date subtractDays(Date date, Integer days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
		return cal.getTime();
	}

	/**
	 * add months to date in java
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonths(Date date, Integer months) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * subtract months to date in java
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date subtractMonths(Date date, Integer months) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -months);
		return cal.getTime();
	}

	public static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		return month;
	}

	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public static boolean isToday(Date date) {
		if (getDayOfMonth(date) == getDayOfMonth(new Date()) && getMonth(date) == getMonth(new Date())
				&& getYear(date) == getYear(new Date())) {
			return true;
		}
		return false;
	}

	public static boolean isPreviousMonth(Date date) {
		if ((getYear(date) < getYear(new Date())) || getMonth(date) < getMonth(new Date())) {
			return true;
		}
		return true;
	}

}