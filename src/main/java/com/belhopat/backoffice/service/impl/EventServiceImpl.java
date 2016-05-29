package com.belhopat.backoffice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.Event;
import com.belhopat.backoffice.repository.EventRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.EventService;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.util.DateUtil;

/**
 * @author BHP_DEV Service layer to implement event and reminders business
 */

@Component
public class EventServiceImpl implements EventService {

	@Autowired
	BaseService baseService;

	@Autowired
	EventRepository eventRepository;

	@Autowired
	MailService mailService;

	@Override
	public DataTablesOutput<Event> getEvents(DataTablesInput input) {

		Specification<Event> specification = new Specification<Event>() {
			@Override
			public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate isNotDeleted = criteriaBuilder.equal(root.get("deleted"), false);
				return criteriaBuilder.and(isNotDeleted);
			}
		};
		DataTablesOutput<Event> dataTablesOutput = eventRepository.findAll(input, specification);
		return dataTablesOutput;
	}

	@Override
	public ResponseEntity<Event> getEvent(Long eventId) {
		Event event = eventRepository.findById(eventId);
		if (event != null) {
			return new ResponseEntity<Event>(event, HttpStatus.OK);
		}
		return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Map<String, String>> saveOrUpdateEvent(Event event) throws MessagingException {
		Map<String, String> responseMap = new HashMap<>();
		if (event != null) {
			eventRepository.save(event);
			List<String> guestsEmails = getGuestsEmailAddresses(event);
			mailService.sendEventInvitaionMail(guestsEmails, "");
		}
		return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.NO_CONTENT);
	}

	private List<String> getGuestsEmailAddresses(Event event) {
		List<Employee> guests = event.getGuests();
		List<String> guestsEmails = new ArrayList<>();
		for (Employee employee : guests) {
			guestsEmails.add(employee.getOfficialEmail());
		}
		return guestsEmails;
	}

	public List<Event> getAllEvents(Event recurEvent) {
		List<Event> events = new ArrayList<>();
//		if (recurEvent.getRecurFrequency().getCode().equals(Constants.NO_REPEAT)) {
//			Event event = getNoRepeatEvent(recurEvent);
//			events.add(event);
//
//		} else if (recurEvent.getRecurFrequency().getCode().equals(Constants.DAILY_REPEAT)) {
//			List<Event> dailyEvents = getDailyEvents(recurEvent);
//			events.addAll(dailyEvents);
//
//		} else if (recurEvent.getRecurFrequency().getCode().equals(Constants.WEEKLY_REPEAT)) {
//			List<Event> weeklyEvents = getWeeklyEvents(recurEvent);
//			events.addAll(weeklyEvents);
//
//		} else if (recurEvent.getRecurFrequency().getCode().equals(Constants.MONTHLY_REPEAT)) {
//			List<Event> monthlyEvents = getMonthlyEvents(recurEvent);
//			events.addAll(monthlyEvents);
//
//		} else if (recurEvent.getRecurFrequency().getCode().equals(Constants.YEARLY_REPEAT)) {
//			List<Event> monthlyEvents = getMonthlyEvents(recurEvent);
//			events.addAll(monthlyEvents);
//
//		}
		return null;
	}

	private Event getNoRepeatEvent(Event recurEvent) {
		Event event = new Event();
		event.setName(recurEvent.getName());
		event.setDescription(recurEvent.getDescription());
		event.setFromDate(recurEvent.getFromDate());
		event.setToDate(recurEvent.getToDate());
		event.setTimeZone(recurEvent.getTimeZone());
		event.setLocation(recurEvent.getLocation());
		return event;
	}

	private List<Event> getDailyEvents(List<Event> recurEvents) {
		List<Event> dailyEvents = new ArrayList<>();
		for (Event recurEvent : recurEvents) {
			Integer recurInterval = recurEvent.getRecurInterval();
			Date eventStartDate = recurEvent.getFromDate();
			Date eventEndDate = recurEvent.getToDate();
			Date recurEndDate = recurEvent.getRecurEndDate();
			do {
				Event event = new Event();
				event.setName(recurEvent.getName());
				event.setDescription(recurEvent.getDescription());
				event.setFromDate(eventStartDate);
				event.setToDate(eventEndDate);
				event.setTimeZone(recurEvent.getTimeZone());
				event.setLocation(recurEvent.getLocation());
				dailyEvents.add(event);
				eventStartDate = DateUtil.addDays(eventStartDate, recurInterval);
				eventEndDate = DateUtil.addDays(eventEndDate, recurInterval);
			} while (eventStartDate.after(recurEndDate));
		}
		return dailyEvents;
	}

	private List<Event> getWeeklyEvents(List<Event> recurEvents) {
		List<Event> dailyEvents = new ArrayList<>();
		for (Event recurEvent : recurEvents) {
			Integer recurInterval = recurEvent.getRecurInterval();
			Date eventStartDate = recurEvent.getFromDate();
			Date eventEndDate = recurEvent.getToDate();
			Date recurEndDate = recurEvent.getRecurEndDate();
			do {
				Event event = new Event();
				event.setName(recurEvent.getName());
				event.setDescription(recurEvent.getDescription());
				event.setFromDate(eventStartDate);
				event.setToDate(eventEndDate);
				event.setTimeZone(recurEvent.getTimeZone());
				event.setLocation(recurEvent.getLocation());
				dailyEvents.add(event);
				eventStartDate = DateUtil.addDays(eventStartDate, recurInterval);
				eventEndDate = DateUtil.addDays(eventEndDate, recurInterval);
			} while (eventStartDate.after(recurEndDate));
		}
		return dailyEvents;
	}

	private List<Event> getMonthlyEvents(List<Event> recurEvents) {
		List<Event> dailyEvents = new ArrayList<>();
		for (Event recurEvent : recurEvents) {
			Integer recurInterval = recurEvent.getRecurInterval();
			Date eventStartDate = recurEvent.getFromDate();
			Date eventEndDate = recurEvent.getToDate();
			Date recurEndDate = recurEvent.getRecurEndDate();
			do {
				Event event = new Event();
				event.setName(recurEvent.getName());
				event.setDescription(recurEvent.getDescription());
				event.setFromDate(eventStartDate);
				event.setToDate(eventEndDate);
				event.setTimeZone(recurEvent.getTimeZone());
				event.setLocation(recurEvent.getLocation());
				dailyEvents.add(event);
				eventStartDate = DateUtil.addMonths(eventStartDate, recurInterval);
				eventEndDate = DateUtil.addMonths(eventEndDate, recurInterval);
			} while (eventStartDate.after(recurEndDate));
		}
		return dailyEvents;
	}
}
