package com.belhopat.backoffice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.Event;

@Service
public interface EventService {

	ResponseEntity<List<Event>> getEvents();

	ResponseEntity<Event> addEvent(Event event);

	ResponseEntity<Event> updateEvent(Event event);
}
