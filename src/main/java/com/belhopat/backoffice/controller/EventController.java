package com.belhopat.backoffice.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.model.Event;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.EventService;

/**
 * @author Belhopat dev team Handler for all event related service calls
 *
 */
@Controller
@RequestMapping("/api/event")
public class EventController {

	@Autowired
	BaseService baseService;

	@Autowired
	EventService eventService;

	@ResponseBody
	@RequestMapping(value = "/getEvents", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getEvents() {
		return eventService.getEvents();
	}

	@ResponseBody
	@RequestMapping(value = "/addEvent", method = RequestMethod.POST)
	public ResponseEntity<Event> addEvent(@RequestBody Event event) throws MessagingException, ParseException {
		return eventService.addEvent(event);
	}

	@ResponseBody
	@RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
	public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
		return eventService.updateEvent(event);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
	public ResponseEntity<Event> deleteEvent(@RequestBody Long eventId) {
		return eventService.deleteEvent(eventId);
	}

	@ResponseBody
	@RequestMapping(value = "/getEmployeesDropDownData", method = RequestMethod.GET)
	public ResponseEntity<Map<String, List<?>>> getEmployeesDropDownData() {
		return eventService.getEmployeesDropDownData();
	}

}