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
	public static final String GENDER = "GENDER";
	public static final String JOINED = "JOINED";
	public static final String VENDOR_CATEGORY = "VNDR_CAT";
	public static final String VENDOR_STATUS = "VNDR_STATS";
	public static final String TDS_CATEGORY = "TDS_CAT";
	public static final String VENDOR_RATING = "VNDR_RATING";
	public static final String POC_DESGN = "POC_DESGN";
	public static final String PO_STATUS = "PO_STATUS";

	/* password reset msg constants */
	public static final String PASS_RESET_SUCC_MSG = "New password has been sent to your registered email.";
	public static final String PASS_RESET_FAIL_MSG = "User account does not exist. Please contact administrator.";

	/* password change msg constants */
	public static final String PASS_CHANGE_SUCC_MSG = "Your password has been changed successfully";
	public static final String PASS_CHANGE_FAIL_MSG = "Invalid credentials entered. Please try again."
			+ " Please try again after some time";

	/* Welcome mail msg constants */
	public static final String WEL_MAIL_SUCC = "Mail has been sent successfully.";
	public static final String WEL_MAIL_FAIL = "An unexpected error occurred. Please try again after some time.";

	/* mail constants */
	public static final String UTF_8 = "utf-8";
	public static final String TEMP_EMAIL_ACCOUNT_FOR_TESTING = "bhptestreceiver@gmail.com";
	public static final String PASS_RESET_MAIL_SUB = "Belhopat Employee Portal Password Reset Request";
	public static final String CAND_REG_SUCC_MAIL_SUB = "New Candidate has been added.";
	public static final String CLIENT_REG_SUCC_MAIL_SUB = "New Client has been added.";
	public static final String EMP_REG_SUCC_MAIL_SUB = "New Employee has been added.";
	public static final String CONS_REG_SUCC_MAIL_SUB = "New Consultant has been added.";
	public static final String EVENT_REG_SUCC_MAIL_SUB = "New Event has been added.";
	public static final String EMPLOYEE_PORTAL_CREDENTIALS = "Belhopat Employee Portal Credentials";
	public static final String CREATE_OFFICIAL_EMAIL = "Action Required - Create Official Email for Registered Employee";

	/* mail templates */
	public static final String DEFAULT_EMAIL_TEMPLATE = "/default-email-template.vm";
	public static final String PASSWORD_RESET_TEMPLATE = "/password-reset-template.vm";
	public static final String CONS_REG_EMAIL_TEMPLATE = "/consultant-reg-email-template.vm";
	public static final String CLIENT_REG_EMAIL_TEMPLATE = "/client-reg-email-template.vm";
	public static final String EMP_REG_EMAIL_TEMPLATE = "/employee-reg-email-template.vm";
	public static final String EVENT_INV_EMAIL_TEMPLATE = "/employee-invitation-email-template.vm";
	public static final String EVENT_REG_EMAIL_TEMPLATE = "/event-reg-email-template.vm";
	public static final String CAND_REG_EMAIL_TEMPLATE = "/candidate-reg-email-template.vm";
	public static final String VENDOR_APP_REJECT_EMAIL_TEMPLATE = "/vendor-reg-email-template.vm";
	public static final String USER_CREATED_EMAIL_TEMPLATE = "/user-created-email-template.vm";
	public static final String CREATE_OFFICIAL_EMAIL_TEMPLATE = "/create-officialEmail-email-template.vm";

	/* mail map keys */
	public static final String CONTENT = "content";
	public static final String GENERATED_PASSWORD = "generatedPassword";
	public static final String PASSWORD_RESET_URL = "passwordResetUrl";
	public static final String LOGIN_URL = "loginUrl";
	public static final String USERNAME = "username";
	public static final String FULL_NAME = "fullName";
	public static final String CONSULTANT = "consultant";
	public static final String CLIENT = "client";
	public static final String EMPLOYEE = "employee";
	public static final String CANDIDATE = "candidate";
	public static final String EVENT = "event";
	public static final String EVENT_START = "eventStart";
	public static final String EVENT_END = "eventEnd";
	public static final String POC = "poc";
	public static final String EMPLOYEE_NAME = "employeeName";
	public static final String EMPLOYEE_ID = "employeeId";
	public static final String CHANGE_PASSWORD_API = "changePassword";
	public static final String OFFICIAL_EMAIL = "officialEmail";

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
	public static final String EMPLOYEES = "EMPLOYEES";
	public static final String TIMEZONE = "TIMEZONE";
	public static final String CLIENTS = "CLIENT";
	public static final String SOURCED_BY = "SOURCEDBY";
	public static final String STATUS = "STATUS";
	public static final String CURRENCY = "CURRENCY";

	/* lookup detail code */
	public static final String EMP_DESIG_HRM = "HRM";
	public static final String EMP_DESIG_BUH = "BUH";
	public static final String EMP_DESIG_AM = "AM";
	public static final String EMP_DESIG_HRR = "HRR";
	public static final String EMP_DESIG_FM = "FM";
	public static final String EMP_DESIG_CEO = "CEO";
	public static final String EMP_DESIG_BDM = "BDM";
	public static final String EMP_STS_INACTIVE = "INACTIVE";
	public static final String EMP_STS_ACTIVE = "ACTIVE";

	/* AOP CONSTANTS */
	public static final String WEBSERVICE_MODULE = "Controller";
	public static final String SERVICE_MODULE = "Service";
	public static final String PERSISTENCE_MODULE = "Repository";

	/* event recur frequency code */
	public static final String NO_REPEAT = "NO_REPEAT";
	public static final String DAILY_REPEAT = "DAILY_REPEAT";
	public static final String WEEKLY_REPEAT = "WEEKLY_REPEAT";
	public static final String MONTHLY_REPEAT = "MONTHLY_REPEAT";
	public static final String YEARLY_REPEAT = "YEARLY_REPEAT";
	public static final String CREATED = "CREATED";
	public static final String GENERATED = "GENERATED";

	/* Alfresco Folder Names */
	public static final String OFFER_LETTERS = "OFFER_LETTERS";

	/* File type constants */
	public static final String PDF_CONTENT_TYPE = "application/pdf";
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String ATTACHMENT = "attachment; filename=";
	public static final String VENDOR = "vendor";

	/* Amazon S3 Constant Names */
	public static final String BUCKET_NAME = "belhopatbackoffice";
	public static final String REIMBURSE_FILE = "Reimburse";
	public static final String BANK_DETAILS = "BankDetails";
	public static final String PASSPORT_DETAILS = "PassportDetails";
	public static final String DRIVING_LICENCE_DETAILS = "DrivingLicenceDetails";
	public static final String PAN_DETAILS = "PanDetails";
	public static final String FOREX_CARD_DETAILS = "ForexCardDetails";

	public static final String OFR_LTR_FILE = "OfferLetter";
	public static final String AWS_ACCEESS_KEY_ID = "AKIAJRCCXXN3JYGSMH6A";
	public static final String AWS_SECRET_KEY = "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";
}
