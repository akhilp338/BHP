
package com.belhopat.backoffice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

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
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.pdf.PDFConstants;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.service.session.MailMessageObject;
import com.belhopat.backoffice.util.Constants;

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
	
	protected static final Logger LOGGER = Logger.getLogger(MailServiceImpl.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.belhopat.backoffice.service.MailService#sendMail(com.belhopat.
	 * backoffice.service.session.MailMessageObject)
	 * sends mail in a thread
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
	 * lang.String, java.lang.String)
	 * sends reseted password
	 */
	@Override
	public void sendPasswordResetMail(User user, String generatedPassword) throws MessagingException {

		Map<String, Object> model = new HashMap < String, Object > ();
		model.put( Constants.GENERATED_PASSWORD, generatedPassword );
		model.put( Constants.USERNAME, user.getUsername());
		
        String emailHtmlBody = generateEmailBodyFromVelocityTemplate( Constants.PASSWORD_RESET_TEMPLATE, model );
        
        String logoResourcePath = "/pdf-resources/" + PDFConstants.LOGO_JPG;
        
		MailMessageObject mailObject = new MailMessageObject(user.getEmail(), MAIL_FROM, Constants.PASS_RESET_MAIL_SUB,
				emailHtmlBody, logoResourcePath, mailSender);
		sendMail(mailObject);
//		velocityEngine.setApplicationAttribute("javax.servlet.ServletContext", servletContext);
	}

	/**
	 * @param passwordResetTemplate
	 * @param model
	 * @return htmlEmailBody
	 * Accepts a velocity template name and model map containing objects to be merged with the template and merges them into a string
	 */
	private String generateEmailBodyFromVelocityTemplate( String templateName, Map<String, Object> model ) {
		String emailHtmlBody = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, templateName, Constants.UTF_8, model);
		return emailHtmlBody;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.MailService#sendCandidateRegMail(java.
	 * lang.String, java.lang.String)
	 * sends mail on candidate registration success
	 */
	@Override
	public void sendCandidateRegMail(String userEmail, String mailContent ) throws MessagingException {

		Map<String, Object> model = new HashMap < String, Object > ();
		model.put( Constants.CONTENT, mailContent );
		String emailHtmlBody = generateEmailBodyFromVelocityTemplate( Constants.DEFAULT_EMAIL_TEMPLATE, model);
		MailMessageObject mailObject = new MailMessageObject(userEmail, MAIL_FROM, Constants.CAND_REG_SUCC_MAIL_SUB,
				emailHtmlBody, mailSender);
		sendMail(mailObject);

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.MailService#sendClientRegMail(java.
	 * lang.String, java.lang.String)
	 * sends mail on client registration success
	 */
	@Override
	public void sendClientRegMail( Client client ) throws MessagingException {

		Map<String, Object> model = new HashMap < String, Object > ();
		model.put( Constants.CLIENT, client );
		model.put( Constants.POC, client.getPoc() );
		String emailHtmlBody = generateEmailBodyFromVelocityTemplate( Constants.CLIENT_REG_EMAIL_TEMPLATE, model);
		MailMessageObject mailObject = new MailMessageObject(Constants.TEMP_EMAIL_ACCOUNT_FOR_TESTING,
				MAIL_FROM, Constants.CLIENT_REG_SUCC_MAIL_SUB,
				emailHtmlBody, mailSender);
		sendMail(mailObject);

	}
	

	@Override
	public void sendEventInvitaionMail(List<String> guestEmails, String emailBody) throws MessagingException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.belhopat.backoffice.service.MailService#sendCandidateRegMail
	 * (com.belhopat.backoffice.model.Candidate, java.lang.Boolean)
	 * sends Mail on candidate and employee registration based on value of isEmployee status flag
	 */
	@Override
	public void sendCandidateRegMail( Candidate candidate, Boolean isEmployee, String employeeId ) throws MessagingException {
		
		MailMessageObject mailObject = null;
		String mailSubject = null;
		String mailTemplate = null;
		Map<String, Object> model = new HashMap < String, Object > ();
		if(!isEmployee){
			model.put( Constants.CANDIDATE, candidate );
			mailSubject = Constants.CAND_REG_SUCC_MAIL_SUB;
			mailTemplate = Constants.CAND_REG_EMAIL_TEMPLATE;

		}else{
			String employeeName = candidate.getFirstName() + " "
					+ candidate.getLastName();
			mailSubject = Constants.EMP_REG_SUCC_MAIL_SUB;
			mailTemplate = Constants.EMP_REG_EMAIL_TEMPLATE;
			model.put( Constants.EMPLOYEES, employeeId );
			model.put( Constants.EMPLOYEE_NAME, employeeName );
		}
		String emailHtmlBody = generateEmailBodyFromVelocityTemplate( mailTemplate, model);
		mailObject = new MailMessageObject(Constants.TEMP_EMAIL_ACCOUNT_FOR_TESTING,
				MAIL_FROM, mailSubject, emailHtmlBody, mailSender);
		sendMail(mailObject);
		
	}

}