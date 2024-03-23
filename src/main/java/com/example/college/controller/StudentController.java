package com.example.college.controller;

import com.example.college.model.Student;
import com.example.college.serviceImpl.StudentsSvcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@EnableAutoConfiguration
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentsSvcImpl studentsSvcImpl;

    @PostMapping("/students/")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        logger.info("received request to add a new student");
        Student newStudent = studentsSvcImpl.addStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable Long id) {
        logger.info("received request to edit student: {}", id);
        Student editedStudent = studentsSvcImpl.editStudent(id, student);
        return new ResponseEntity<>(editedStudent, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        logger.info("received request to get student: {}", id);
        Student retrievedStudent = studentsSvcImpl.getStudent(id);
        return new ResponseEntity<>(retrievedStudent, HttpStatus.OK);
    }

    @GetMapping("/students/useCollegeBus")
    public ResponseEntity<List<Student>> findStudentsUsingCollegeBus() {
        logger.info("received request to find students using college bus");
        List<Student> retrievedStudent = studentsSvcImpl.getCollegeBusData();
        return new ResponseEntity<>(retrievedStudent, HttpStatus.OK);
    }

    @GetMapping("/students/find/{dept}")
    public ResponseEntity<List<Student>> findStudentsByDept(@PathVariable String dept) {
        logger.info("received request to find students by dept");
        List<Student> retrievedStudent = studentsSvcImpl.getStudentsByDept(dept);
        return new ResponseEntity<>(retrievedStudent, HttpStatus.OK);
    }

    @GetMapping("/students/find/{dept}/{year}")
    public ResponseEntity<List<Student>> findStudentsByDeptAndYear(@PathVariable String dept,
                                                                   @PathVariable int year) {
        logger.info("received request to find students by dept and year");
        List<Student> retrievedStudent = studentsSvcImpl.getStudentsByDeptAndYear(dept, year);
        return new ResponseEntity<>(retrievedStudent, HttpStatus.OK);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        logger.info("received request to delete student: {}", id);
        studentsSvcImpl.deleteStudent(id);
        return new ResponseEntity<>("Student - " + id + " deleted successfully!", HttpStatus.OK);
    }
}
