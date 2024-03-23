package com.example.college.controller;

import com.example.college.model.Events;
import com.example.college.serviceImpl.EventsSvcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@EnableAutoConfiguration
public class EventsController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    EventsSvcImpl eventsSvc;

    @PostMapping("/events/")
    public ResponseEntity<Events> addEvents(@RequestBody Events events) {
        logger.info("received request to add a new event");
        Events newEvent = eventsSvc.addEvent(events);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @GetMapping("/events/")
    public ResponseEntity<List<Events>> getAllEvents() {
        logger.info("received request to get all the events");
        List<Events> newEvent = eventsSvc.getEvents();
        return new ResponseEntity<>(newEvent, HttpStatus.OK);
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<Events> getEvent(@PathVariable Long eventId) {
        logger.info("received request to get an event");
        Events newEvent = eventsSvc.getEventById(eventId);
        return new ResponseEntity<>(newEvent, HttpStatus.OK);
    }

    @PutMapping("/events/{eventId}/{studentId}")
    public ResponseEntity<Events> addInCharge(@PathVariable Long eventId, @PathVariable Long studentId) {
        logger.info("received request to assign an in-charge to this event");
        Events newEvent = eventsSvc.addInCharge(eventId, studentId);
        return new ResponseEntity<>(newEvent, HttpStatus.OK);
    }

    @DeleteMapping("/events/{eventId}/")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) {
        logger.info("received request to delete an event");
        eventsSvc.deleteEvent(eventId);
        return new ResponseEntity<>("Deleted event successfully", HttpStatus.OK);
    }
}
