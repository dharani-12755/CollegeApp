package com.example.college.controller;

import com.example.college.model.Grades;
import com.example.college.model.SemesterGrades;
import com.example.college.serviceImpl.GradesSvcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@EnableAutoConfiguration
public class GradesController {

    private static final Logger logger = LoggerFactory.getLogger(GradesController.class);

    @Autowired
    GradesSvcImpl gradesSvc;

    @PostMapping("/students/{studentId}/semester/{currentSemester}/grades")
    public ResponseEntity<List<SemesterGrades>> addgrades(@RequestBody Map<String, String> marks,
                                                    @PathVariable Long studentId,
                                                    @PathVariable int currentSemester) {
        logger.info("received request to add a grades for student - {} for the semester - {}",
                studentId, currentSemester);
        List<SemesterGrades> allGrades =  gradesSvc.addGrade(studentId, currentSemester, marks);
        return new ResponseEntity<>(allGrades, HttpStatus.CREATED);
    }

    @GetMapping("/students/{studentId}/semester/{currentSemester}/grades")
    public ResponseEntity<List<SemesterGrades>> getGrades(@PathVariable Long studentId,
                                                          @PathVariable int currentSemester) {
        logger.info("received request to get grades for student - {} for the semester - {}",
                studentId, currentSemester);
        List<SemesterGrades> allGrades =  gradesSvc.getGrades(studentId, currentSemester);
        return new ResponseEntity<>(allGrades, HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}/semester/all/grades")
    public ResponseEntity<Grades> getAllGrades(@PathVariable Long studentId) {
        logger.info("received request to get grades for student - {}",
                studentId);
        Grades allGrades =  gradesSvc.getGrades(studentId);
        return new ResponseEntity<>(allGrades, HttpStatus.OK);
    }
}
