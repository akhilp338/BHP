package com.belhopat.backoffice.service;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.Event;

@Service
public interface EventService {

	public DataTablesOutput<Event> getEvents(DataTablesInput input);

	public ResponseEntity<Event> getEvent(Long eventId);

	public ResponseEntity<Map<String, String>> saveOrUpdateEvent(Event event) throws MessagingException;

}
