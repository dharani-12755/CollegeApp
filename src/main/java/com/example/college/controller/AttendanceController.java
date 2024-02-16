package com.example.college.controller;

import com.example.college.model.Attendance;
import com.example.college.model.Student;
import com.example.college.serviceImpl.AttendanceSvcImpl;
import com.example.college.serviceImpl.StudentsSvcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class AttendanceController {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    @Autowired
    AttendanceSvcImpl attendanceSvc;

    @PutMapping("/students/{id}/absenceDays/{absenceDays}")
    public ResponseEntity<Attendance> addAbsences(@PathVariable Long id, @PathVariable int absenceDays) {
        logger.info("received request to add absence for - {} days", absenceDays);
        Attendance attendance = attendanceSvc.addAbsences(id, absenceDays);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    @GetMapping("/students/{id}/attendance")
    public ResponseEntity<Attendance> getAttendance(@PathVariable Long id) {
        logger.info("received request to get attendance record for student - {}", id);
        Attendance attendance = attendanceSvc.getAttendance(id);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }
}
