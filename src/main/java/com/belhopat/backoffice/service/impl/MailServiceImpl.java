
package com.belhopat.backoffice.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.belhopat.backoffice.model.Candidate;
import com.belhopat.backoffice.model.Client;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.Event;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.pdf.PDFConstants;
import com.belhopat.backoffice.repository.UserRepository;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.service.session.MailMessageObject;
import com.belhopat.backoffice.util.Constants;
import com.belhopat.backoffice.util.DateUtil;
import com.belhopat.backoffice.util.servlet.BelhopatServletContextInfo;

/**
 * @author Sreekesh Service for sending and syncing mails
 */
@Async
@Service("mailService")
@PropertySource("classpath:email.properties")
public class MailServiceImpl implements MailService {

	@Value("#{emailConfiguration['mail.smtp.username']}")
	private String MAIL_FROM;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	ThreadPoolTaskExecutor threadExecutor;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	UserRepository userRepository;

	protected static final Logger LOGGER = Logger.getLogger(MailServiceImpl.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.belhopat.backoffice.service.MailService#sendMail(com.belhopat.
	 * backoffice.service.session.MailMessageObject) sends mail in a thread
	 */
	@Async
	@Override
	public void sendMail(MailMessageObject mailObj) {

		Runnable runnableWorker = new Runnable() {
			@Override
			public void run() {
				try {
					mailSender.send(mailObj.getMimeMessage());
				} catch (Exception e) {
					LOGGER.error("Exception " + e);
				}
			}
		};
		threadExecutor.execute(runnableWorker);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.MailService#sendPasswordResetMail(java.
	 * lang.String, java.lang.String) sends reseted password
	 */
	@Override
	public void sendPasswordResetMail(User user) throws MessagingException {
		Map<String, Object> model = new HashMap<String, Object>();
		String passwordResetURL = BelhopatServletContextInfo.getDeployURL() + Constants.CHANGE_PASSWORD_API + "/"
				+ user.getForgotPasswordToken();
		model.put(Constants.GENERATED_PASSWORD, user.getPassword());
		model.put(Constants.PASSWORD_RESET_URL, passwordResetURL);
		model.put(Constants.USERNAME, user.getUsername());

		String emailHtmlBody = generateEmailBodyFromVelocityTemplate(Constants.PASSWORD_RESET_TEMPLATE, model);
		String logoResourcePath = "/pdf-resources/" + PDFConstants.LOGO_JPG;
		
		InternetAddress[] forDebugEmail = getTempEmailMailingList( Collections.singletonList(user.getEmail()));
		InternetAddress[] forDebugEmailCC = getTempEmailMailingListForCC( null );
		
		MailMessageObject mailObject = new MailMessageObject(forDebugEmail, forDebugEmailCC,  MAIL_FROM, Constants.PASS_RESET_MAIL_SUB,
				emailHtmlBody, logoResourcePath, mailSender);
		sendMail(mailObject);
	}

	/**
	 * @param templateName
	 * @param model
	 * @return htmlEmailBody Accepts a velocity template name and model map
	 *         containing objects to be merged with the template and merges them
	 *         into a string
	 */
	private String generateEmailBodyFromVelocityTemplate(String templateName, Map<String, Object> model) {
		String emailHtmlBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName,
				Constants.UTF_8, model);
		return emailHtmlBody;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.belhopat.backoffice.service.MailService#sendClientRegMail(java.
	 * lang.String, java.lang.String) sends mail on client registration success
	 */
	@Override
	public void sendClientRegMail(Client client) throws MessagingException {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.CLIENT, client);
		model.put(Constants.POC, client.getPoc());
		String emailHtmlBody = generateEmailBodyFromVelocityTemplate(Constants.CLIENT_REG_EMAIL_TEMPLATE, model);
		String logoResourcePath = "/pdf-resources/" + PDFConstants.LOGO_JPG;
		
		List < String > clientRegNotificationList = new ArrayList < String > (1);
		//TODO GET CEO mail id here	
		clientRegNotificationList.add( "bhptestreceiver@gmail.com" );
		clientRegNotificationList.add( client.getBussUnitHead().getOfficialEmail() != null ? client.getBussUnitHead().getOfficialEmail() : null );
		
		InternetAddress[] forDebugEmail = getTempEmailMailingList(clientRegNotificationList);
		InternetAddress[] forDebugEmailCC = getTempEmailMailingListForCC( null );
		
		
		MailMessageObject mailObject = new MailMessageObject(forDebugEmail, forDebugEmailCC, MAIL_FROM,
				Constants.CLIENT_REG_SUCC_MAIL_SUB, emailHtmlBody, logoResourcePath, mailSender);
		sendMail(mailObject);

	}

	@Override
	public void sendCandidateRegMail(Candidate candidate) throws MessagingException {
		MailMessageObject mailObject = null;
		String mailSubject = null;
		String mailTemplate = null;
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.CANDIDATE, candidate);
		mailSubject = Constants.CAND_REG_SUCC_MAIL_SUB;
		mailTemplate = Constants.CAND_REG_EMAIL_TEMPLATE;
		String emailHtmlBody = generateEmailBodyFromVelocityTemplate(mailTemplate, model);
		String logoResourcePath = "/pdf-resources/" + PDFConstants.LOGO_JPG;
		
		InternetAddress[] forDebugEmail = getTempEmailMailingList(null);
		InternetAddress[] forDebugEmailCC = getTempEmailMailingListForCC( null );
		
		mailObject = new MailMessageObject(forDebugEmail, forDebugEmailCC, MAIL_FROM, mailSubject, emailHtmlBody, logoResourcePath, mailSender);
		sendMail(mailObject);

	}

	@Override
	public void sendEmployeeRegMail(Employee employee) throws MessagingException {
		MailMessageObject mailObject = null;
		String mailSubject = null;
		String mailTemplate = null;
		String fullName = 
				(employee.getEmployeeMaster().getFirstName() != null ? employee.getEmployeeMaster().getFirstName() + " " : " ") + 
				(employee.getEmployeeMaster().getLastName() != null ? employee.getEmployeeMaster().getLastName() : "");
		Map<String, Object> model = new HashMap<String, Object>();
		mailSubject = Constants.EMP_REG_SUCC_MAIL_SUB;
		mailTemplate = Constants.EMP_REG_EMAIL_TEMPLATE;
		model.put(Constants.EMPLOYEE, employee);
		model.put(Constants.EMPLOYEE_NAME, fullName);
		String emailHtmlBody = generateEmailBodyFromVelocityTemplate(mailTemplate, model);
		String logoResourcePath = "/pdf-resources/" + PDFConstants.LOGO_JPG;
		
		InternetAddress[] forDebugEmail = getTempEmailMailingList(null);
		InternetAddress[] forDebugEmailCC = getTempEmailMailingListForCC( null );
		
		mailObject = new MailMessageObject(forDebugEmail, forDebugEmailCC, MAIL_FROM, mailSubject, emailHtmlBody, logoResourcePath, mailSender);
		sendMail(mailObject);

	}

	/**
	 * @return temp list of email addresses AS CC.
	 * @throws AddressException
	 */
	public InternetAddress[] getTempEmailMailingListForCC(List <String> receiverEmail) throws AddressException {
		List<InternetAddress> forDebugList = new ArrayList<InternetAddress>();
		forDebugList.add(new InternetAddress("sreekesh@belhopat.com"));
		forDebugList.add(new InternetAddress("sreekeshd@gmail.com"));
		forDebugList.add(new InternetAddress("akhil@belhopat.com"));
		forDebugList.add(new InternetAddress("akhilp338@gmail.com"));
		forDebugList.add(new InternetAddress("sujith@belhopat.com"));
		forDebugList.add(new InternetAddress("sujithkvclt@gmail.com"));
		forDebugList.add(new InternetAddress("prince@belhopat.com"));
		forDebugList.add(new InternetAddress("princegracys@gmail.com"));
		forDebugList.add(new InternetAddress("iamshintomjose@gmail.com"));
		forDebugList.add(new InternetAddress("shinto@belhopat.com"));
		if (receiverEmail != null) {
			for (String email: receiverEmail) {
				forDebugList.add(new InternetAddress(email));
			}
		}
		InternetAddress[] forDebugEmail = new InternetAddress[forDebugList.size()];
		forDebugEmail = forDebugList.toArray(forDebugEmail);
		return forDebugEmail;
	}
	
	/**
	 * @return temp list of email addresses.
	 * @throws AddressException
	 */
	public InternetAddress[] getTempEmailMailingList(List <String> receiverEmail) throws AddressException {
		List<InternetAddress> forDebugList = new ArrayList<InternetAddress>();
		forDebugList.add(new InternetAddress(Constants.TEMP_EMAIL_ACCOUNT_FOR_TESTING));
		if (receiverEmail != null) {
			for (String email: receiverEmail) {
				forDebugList.add(new InternetAddress(email));
			}
		}
		InternetAddress[] forDebugEmail = new InternetAddress[forDebugList.size()];
		forDebugEmail = forDebugList.toArray(forDebugEmail);
		return forDebugEmail;
	}

	@Override
	public void sendEventInvitationMail(Event event) throws Exception {
		List <Long> userIds = new ArrayList<Long> (1);
		List <String> emailList = new ArrayList <String> (1);
		if( event.getGuestList() != null ){
			for (User user : event.getGuestList()) {
				userIds.add( user.getId() );
			}
			emailList = userRepository.findEmailsByIdList( userIds );
		}
		else{
			throw new Exception( "No user ids specified for guest list");
		}
		MailMessageObject mailObject = null;
		String mailSubject = null;
		String mailTemplate = null;
		String eventTitle = event.getTitle()==null?"":event.getTitle();
		String eventStart = event.getStart()==null?"":DateUtil.toMMMMddYYYY(event.getStart()) ;
		String eventEnd = event.getEnd()==null?"":DateUtil.toMMMMddYYYY(event.getEnd()) ;
		mailSubject = "Event Invitation - " + eventTitle + " scheduled on "+ eventStart;
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("event",event);
		model.put("start",eventStart);
		model.put("end",eventEnd);
		mailTemplate = Constants.EMP_REG_EMAIL_TEMPLATE;
		String emailHtmlBody = generateEmailBodyFromVelocityTemplate(mailTemplate, model);
		String logoResourcePath = "/pdf-resources/" + PDFConstants.LOGO_JPG;
		
		InternetAddress[] emailIds = getTempEmailMailingList( emailList );
		InternetAddress[] forDebugEmailCC = getTempEmailMailingListForCC( null );
		
		mailObject = new MailMessageObject(emailIds, forDebugEmailCC, MAIL_FROM, mailSubject, emailHtmlBody, logoResourcePath, mailSender);
		sendMail(mailObject);

	}

	@Override
	public void sendWelcomeMail(Employee employee) throws MessagingException {
		Map<String, Object> model = new HashMap<String, Object>();
		String passwordResetURL = BelhopatServletContextInfo.getDeployURL() + Constants.CHANGE_PASSWORD_API + "/"
				+ employee.getEmployeeUser().getForgotPasswordToken();
		String fullName = 
				(employee.getEmployeeMaster().getFirstName() != null ? employee.getEmployeeMaster().getFirstName() + " " : " ") + 
				(employee.getEmployeeMaster().getLastName() != null ? employee.getEmployeeMaster().getLastName() : "");
		model.put(Constants.FULL_NAME, fullName );
		model.put(Constants.USERNAME, employee.getEmployeeUser().getUsername());
		model.put(Constants.GENERATED_PASSWORD, employee.getEmployeeUser().getPassword());
		model.put(Constants.OFFICIAL_EMAIL, employee.getEmployeeUser().getEmail() );
		model.put(Constants.PASSWORD_RESET_URL, passwordResetURL);

		String emailHtmlBody = generateEmailBodyFromVelocityTemplate(Constants.USER_CREATED_EMAIL_TEMPLATE, model);
		String logoResourcePath = "/pdf-resources/" + PDFConstants.LOGO_JPG;
		
		InternetAddress[] forDebugEmail = getTempEmailMailingList( Collections.singletonList(employee.getEmployeeMaster().getPersonalEmail()));
		InternetAddress[] forDebugEmailCC = getTempEmailMailingListForCC( null );
		
		MailMessageObject mailObject = new MailMessageObject(forDebugEmail, forDebugEmailCC, MAIL_FROM, Constants.EMPLOYEE_PORTAL_CREDENTIALS,
				emailHtmlBody, logoResourcePath, mailSender);
		sendMail(mailObject);
		
	}

	@Override
	public void sendCreateOfficialEmail(Employee employee) throws MessagingException {
		MailMessageObject mailObject = null;
		String mailSubject = null;
		String mailTemplate = null;
		String fullName = 
				(employee.getEmployeeMaster().getFirstName() != null ? employee.getEmployeeMaster().getFirstName() + " " : " ") + 
				(employee.getEmployeeMaster().getLastName() != null ? employee.getEmployeeMaster().getLastName() : "");
		String loginUrl = BelhopatServletContextInfo.getDeployURL() ;
		Map<String, Object> model = new HashMap<String, Object>();
		mailSubject = Constants.CREATE_OFFICIAL_EMAIL;
		mailTemplate = Constants.CREATE_OFFICIAL_EMAIL_TEMPLATE;
		model.put(Constants.EMPLOYEE, employee);
		model.put(Constants.EMPLOYEE_NAME, fullName);
		model.put(Constants.LOGIN_URL, loginUrl);
		String emailHtmlBody = generateEmailBodyFromVelocityTemplate( mailTemplate, model);
		String logoResourcePath = "/pdf-resources/" + PDFConstants.LOGO_JPG;
		
		InternetAddress[] forDebugEmail = getTempEmailMailingList(null);
		InternetAddress[] forDebugEmailCC = getTempEmailMailingListForCC( null );
		
		mailObject = new MailMessageObject(forDebugEmail,forDebugEmailCC, MAIL_FROM, mailSubject, emailHtmlBody, logoResourcePath, mailSender);
		sendMail(mailObject);
		
	}

}