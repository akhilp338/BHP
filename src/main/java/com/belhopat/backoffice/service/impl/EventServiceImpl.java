package com.belhopat.backoffice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.model.Event;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.EventRepository;
import com.belhopat.backoffice.service.EventService;
import com.belhopat.backoffice.session.SessionManager;

/**
 * @author BHP_DEV Service layer to implement event and reminders business
 */

@Component
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository eventRepository;

	@Override
	public ResponseEntity<List<Event>> getEvents() {
		User loggedInUser = SessionManager.getCurrentUser();
		List<Event> events = eventRepository.getEvents(loggedInUser.getId());
		if (events != null) {
			return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
		}
		return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Event> addEvent(Event event) {
		User loggedInUser = SessionManager.getCurrentUser();
		event.setBaseAttributes(loggedInUser);
		event = eventRepository.save(event);
		if (event != null) {
			return new ResponseEntity<Event>(event, HttpStatus.OK);
		}
		return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
	}
	
	@Override
	public ResponseEntity<Event> updateEvent(Event editedEvent) {
		User loggedInUser = SessionManager.getCurrentUser();
		Event event = eventRepository.findById(editedEvent.getId());
		if (event != null) {
			event.setStart(editedEvent.getStart());
			event.setEnd(editedEvent.getEnd());
			event.setTitle(editedEvent.getTitle());
			event.setUpdateAttributes(loggedInUser);
			event = eventRepository.save(event);
			return new ResponseEntity<Event>(event, HttpStatus.OK);
		}
		return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
	}
	
	

}
