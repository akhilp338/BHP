package com.belhopat.backoffice.service;

import java.text.ParseException;

import javax.mail.MessagingException;

import com.belhopat.backoffice.model.Candidate;
import com.belhopat.backoffice.model.Client;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.Event;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.service.session.MailMessageObject;

public interface MailService {

	public void sendMail(MailMessageObject mailObj);

	public void sendPasswordResetMail(User user) throws MessagingException;

	public void sendCandidateRegMail(Candidate candidate) throws MessagingException;

	public void sendEmployeeRegMail(Employee employee) throws MessagingException;

	public void sendClientRegMail(Client client) throws MessagingException;

	public void sendEventInvitationMail(Event event) throws MessagingException, ParseException, Exception;

	public void sendWelcomeMail(Employee employee) throws MessagingException;

	public void sendCreateOfficialEmail(Employee employee) throws MessagingException;

}
