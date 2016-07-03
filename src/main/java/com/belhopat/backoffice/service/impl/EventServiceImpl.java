package com.belhopat.backoffice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.model.Event;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.EmployeeRepository;
import com.belhopat.backoffice.repository.EventRepository;
import com.belhopat.backoffice.service.EventService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.Constants;
import com.belhopat.backoffice.util.DateUtil;
import com.mysema.query.types.Constant;

/**
 * @author BHP_DEV Service layer to implement event and reminders business
 */

@Component
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public ResponseEntity<List<Event>> getEvents() {
		User loggedInUser = SessionManager.getCurrentUser();
		List<Event> events = eventRepository.getEvents(loggedInUser.getId());
		//insertSampleEvents();
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

	private void insertSampleEvents() {
		User loggedInUser = SessionManager.getCurrentUser();
		List<Event> events = new ArrayList<>();
		Event event1 = new Event();
		event1.setTitle("First Event");
		event1.setStart(new Date());
		event1.setEnd(DateUtil.addDays(new Date(), 1));
		event1.setBaseAttributes(loggedInUser);
		events.add(event1);
		Event event2 = new Event();
		event2.setTitle("Second Event");
		event2.setStart(DateUtil.addDays(new Date(), 5));
		event2.setEnd(DateUtil.addDays(new Date(), 6));
		event2.setBaseAttributes(loggedInUser);
		events.add(event2);
		Event event3 = new Event();
		event3.setTitle("third Event");
		event3.setStart(DateUtil.addDays(new Date(), 7));
		event3.setEnd(DateUtil.addDays(new Date(), 8));
		event3.setBaseAttributes(loggedInUser);
		events.add(event3);
		eventRepository.save(events);
	}

	@Override
	public ResponseEntity<Map<String, List<?>>> getEmployeesDropDownData() {
		Map<String, List<?>> dropDownMap = new HashMap<>();
		List<ResponseObject> employees = employeeRepository.getEmployeeNameAndEmailData();
		dropDownMap.put(Constants.EMPLOYEES, employees);
		return new ResponseEntity<Map<String, List<?>>>(dropDownMap, HttpStatus.OK);
	}

}
