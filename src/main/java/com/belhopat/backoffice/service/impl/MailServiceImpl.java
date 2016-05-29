
package com.belhopat.backoffice.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
	public void sendPasswordResetMail(String userEmail, String generatedPassword) throws MessagingException {

		Map<String, Object> model = new HashMap < String, Object > ();
		model.put( Constants.CONTENT, "Your new password is: " );
		model.put( Constants.GENERATED_PASSWORD, generatedPassword );
        String emailHtmlBody = generateEmailBodyFromVelocityTemplate( Constants.DEFAULT_EMAIL_TEMPLATE, model );
		MailMessageObject mailObject = new MailMessageObject(userEmail, MAIL_FROM, Constants.PASS_RESET_MAIL_SUB,
				emailHtmlBody, mailSender);
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
                velocityEngine, Constants.DEFAULT_EMAIL_TEMPLATE, Constants.UTF_8, model);
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

	@Override
	public void sendEventInvitaionMail(List<String> guestEmails, String emailBody) throws MessagingException {
		// TODO Auto-generated method stub
		
	}

}