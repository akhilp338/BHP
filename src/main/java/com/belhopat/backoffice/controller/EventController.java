package com.belhopat.backoffice.controller;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belhopat.backoffice.dto.RequestObject;
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

	public DataTablesOutput<Event> getEvents(@Valid DataTablesInput input) {
		return eventService.getEvents(input);
	}

	@ResponseBody
	@RequestMapping(value = "/getEvent", method = RequestMethod.POST)

	public ResponseEntity<Event> getEvent(@RequestBody RequestObject requestObject) {
		return eventService.getEvent(requestObject.getId());
	}

	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateCandidate", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> saveOrUpdateCandidate(@RequestBody Event event) throws MessagingException {
		return eventService.saveOrUpdateEvent(event);
	}

	/**
	 * @return Map of dropdown data gets a key value pair list of drop down data
	 */
	@ResponseBody
	@RequestMapping(value = "/getDropDownData", method = RequestMethod.POST)

	public ResponseEntity<Map<String, List<?>>> getDropDownData() {
		return baseService.getCandidateDropDownData();
	}

}