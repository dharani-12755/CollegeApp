package com.example.college.serviceImpl;

import com.example.college.constants.ErrorConstants;
import com.example.college.errors.ApiError;
import com.example.college.errors.EntityNotFoundException;
import com.example.college.model.Events;
import com.example.college.model.Student;
import com.example.college.repository.EventsRepo;
import com.example.college.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsSvcImpl {

    @Autowired
    EventsRepo eventsRepo;

    @Autowired
    StudentsSvcImpl studentsSvc;

    public Events addEvent(Events events) {
        events.setId(IdGenerator.generateId());
        return eventsRepo.save(events);
    }

    public List<Events> getEvents() {
        return eventsRepo.findAll();
    }

    public Events getEventById(Long eventId) {
        Optional<Events> events = eventsRepo.findById(eventId);
        if (events.isPresent()) {
            return events.get();
        } else {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                    ErrorConstants.ErrorCode.EVENT_NOT_FOUND.getCode(),
                    ErrorConstants.ErrorCode.EVENT_NOT_FOUND.getMessage());
            throw new EntityNotFoundException(apiError, eventId);
        }
    }

    public Events addInCharge(Long eventId, Long studentId) {
        Student student = studentsSvc.getStudent(studentId);
        Events events = getEventById(eventId);
        events.setInCharge(student.getName());
        return eventsRepo.save(events);
    }

    public void deleteEvent(Long eventId) {
        eventsRepo.deleteById(eventId);
    }
}
