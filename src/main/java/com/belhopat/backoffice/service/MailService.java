package com.belhopat.backoffice.service;

import java.util.List;

import javax.mail.MessagingException;

import com.belhopat.backoffice.model.Candidate;
import com.belhopat.backoffice.model.Client;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.service.session.MailMessageObject;

public interface MailService {

	public void sendMail(MailMessageObject mailObj);

	public void sendPasswordResetMail( User user, String emailBody ) throws MessagingException;

	public void sendCandidateRegMail( String userEmail, String emailBody ) throws MessagingException;

	public void sendEventInvitaionMail( List<String> guestEmails, String emailBody ) throws MessagingException;

	public void sendClientRegMail( Client client ) throws MessagingException;

	public void sendCandidateRegMail(Candidate candidate, Boolean isEmployee, String employeeId) throws MessagingException;

}
