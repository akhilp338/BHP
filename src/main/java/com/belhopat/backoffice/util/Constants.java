package com.belhopat.backoffice.util;

public class Constants {

	/* lookup key */
	public static final String DIVISION = "DIV";
	public static final String DESIGNATION = "DESIG";
	public static final String PURPOSE = "PURPO";
	public static final String BLOOD_GROUP = "BG";
	public static final String EMPLOYMENT_STATUS = "ESTATS";
	public static final String FAMILY_MEMBER = "FAMMEM";
	public static final String CLIENT_STATUS = "CLSTATS";

	/* password reset msg constants */
	public static final String PASS_RESET_SUCC_MSG = "New password has been sent to your registered email.";
	public static final String PASS_RESET_FAIL_MSG = "User account does not exist. Please contact administrator.";

	/* mail constants */
	public static final String UTF_8 = "ascii";
	public static final String PASS_RESET_MAIL_SUB = "Password Reset Mail";
	public static final String CAND_REG_SUCC_MAIL_SUB = "Welcome to Belhopat Global Services";
	
	/* mail templates */
	public static final String DEFAULT_EMAIL_TEMPLATE = "/default-email-template.vm";

	/* mail map keys */
	public static final String CONTENT = "content";
	public static final String GENERATED_PASSWORD = "generatedPassword";
	
	/* data constants */
	public static final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
	public static final String DD_MM_YYYY_HH_MM = "dd-MM-yyyy HH:mm";
	public static final String DD_MM_YYYY = "dd-MM-yyyy";
	public static final String MMMM_DD_YYYY = "MMMM dd, yyyy";
	public static final String MMMM_DD_YYYY_HH_MM = "MMMM dd, yyyy HH:mm";

	/* map keys */
	public static final String COUNTRY = "COUNTRY";
	public static final String CITY = "CITY";
	public static final String STATE = "STATE";
	public static final String SKILL = "SKILL";

	/* lookup detail code */
	public static final String EMP_DESIG_HRM = "HRM";
	public static final String EMP_DESIG_BUH = "BUH";
	public static final String EMP_DESIG_AM = "AM";
	public static final String EMP_DESIG_HRR = "HRR";
	public static final String EMP_DESIG_FM = "FM";
	public static final String EMP_DESIG_CEO = "CEO";
	public static final String EMP_STS_INACTIVE = "INACTIVE";
	
	/*	AOP CONSTANTS */
	public static final String WEBSERVICE_MODULE = "Controller";
	public static final String SERVICE_MODULE = "Service";
	public static final String PERSISTENCE_MODULE = "Repository";

	/* event recur frequency code */
	public static final String NO_REPEAT = "NO_REPEAT";
	public static final String DAILY_REPEAT = "DAILY_REPEAT";
	public static final String WEEKLY_REPEAT = "WEEKLY_REPEAT";
	public static final String MONTHLY_REPEAT = "MONTHLY_REPEAT";
	public static final String YEARLY_REPEAT = "YEARLY_REPEAT";

}
