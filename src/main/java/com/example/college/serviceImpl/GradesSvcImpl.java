package com.example.college.serviceImpl;

import com.example.college.constants.ErrorConstants;
import com.example.college.errors.ApiError;
import com.example.college.errors.EntityNotFoundException;
import com.example.college.model.Grades;
import com.example.college.model.SemesterGrades;
import com.example.college.repository.GradesRepo;
import com.example.college.repository.SemesterGradesRepo;
import com.example.college.utils.IdGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class GradesSvcImpl {

    @Autowired
    GradesRepo gradesRepo;

    @Autowired
    SemesterGradesRepo semesterGrades;

    public List<SemesterGrades> addGrade(Long studentId, int currentSemester, Map<String, String> marks) {
        Long semesterId = IdGenerator.generateId();
        Grades grades = new Grades();
        grades.setStudentId(studentId);
        grades.setSemester(currentSemester);
        grades.setCgpa("currentSemester");
        grades.setPerformance("currentSemester");
        Pair<List<SemesterGrades>, AtomicReference<Double>> allMarks
                = convertToSemesterGrades(marks, semesterId, currentSemester);
        grades.setGpa(allMarks.getSecond().get());
        return saveSemesterGrades(allMarks.getFirst(), grades);
    }

    public Grades getGrades(Long studentId, int currentSemester) {
        Optional<Grades> grades = gradesRepo.findByStudentIdAndSemester(studentId, currentSemester);

        if(grades.isPresent()) {
            return grades.get();
        } else {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                    ErrorConstants.ErrorCode.STUDENT_NOT_FOUND.getCode(),
                    ErrorConstants.ErrorCode.STUDENT_NOT_FOUND.getMessage());
            throw new EntityNotFoundException(apiError, studentId);
        }
    }

    public static Pair<List<SemesterGrades>, AtomicReference<Double>> convertToSemesterGrades(Map<String, String> marks,
                                                                                              Long semesterId, int currentSemester) {
        List<SemesterGrades> semesterGradesList = new ArrayList<>();
        AtomicReference<Double> gpa = new AtomicReference<>((double) 0);
        marks.forEach((subject, grade) -> {
            gpa.set(calculateGPA(grade, gpa.get()));
            SemesterGrades semesterGrades = new SemesterGrades();
            semesterGrades.setSemesterId(semesterId);
            semesterGrades.setSemester(currentSemester);
            semesterGrades.setSubject(subject);
            semesterGrades.setGrade(grade);
            semesterGradesList.add(semesterGrades);
        });
        return Pair.of(semesterGradesList, gpa);
    }

    @Transactional
    public List<SemesterGrades> saveSemesterGrades(List<SemesterGrades> allMarks, Grades grades) {
        List<SemesterGrades> addedSemesterGrades = semesterGrades.saveAll(allMarks);
        gradesRepo.save(grades);
        return addedSemesterGrades;
    }

    public static double calculateGPA(String grade, double gpa) {
        return switch (grade.toUpperCase()) {
            case "S" -> {
                double sPoints = 10.0;
                yield gpa + sPoints;
            }
            case "A" -> {
                double aPoints = 9.0;
                yield gpa + aPoints;
            }
            default -> gpa;
        };
    }
}
