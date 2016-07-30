package com.belhopat.backoffice.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.Event;

@Service
public interface EventService {

	ResponseEntity<List<Event>> getEvents();

	ResponseEntity<Event> addEvent(Event event) throws MessagingException, ParseException;

	ResponseEntity<Event> updateEvent(Event event);

	ResponseEntity<Map<String, List<?>>> getEmployeesDropDownData();

	ResponseEntity<Event> deleteEvent(Long eventId);
}
