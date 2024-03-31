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
        Optional<Grades> existingGrades = gradesRepo.findByStudentId(studentId);

        List<SemesterGrades> allGrades = semesterGrades.findByStudentIdAndSemester(studentId, currentSemester);
        if (!allGrades.isEmpty()) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                    ErrorConstants.ErrorCode.GRADES_PUBLISHED_FOR_THIS_SEM.getCode(),
                    ErrorConstants.ErrorCode.GRADES_PUBLISHED_FOR_THIS_SEM.getMessage());
            throw new EntityNotFoundException(apiError, studentId);
        }
        Grades grades = new Grades();
        grades.setStudentId(studentId);
        Pair<List<SemesterGrades>, Double> allMarks
                = convertToSemesterGrades(marks, studentId, currentSemester);
        double gpa = allMarks.getSecond();
        double existingGpa = 0;
        if (existingGrades.isPresent()) {
            existingGpa = existingGrades.get().getGpa();
        }
        double cgpa = (gpa + existingGpa)/2;
        cgpa = Double.parseDouble(String.format("%.2f", cgpa));
        double perc = cgpa * 10.0;
        perc = Double.parseDouble(String.format("%.2f", perc));
        grades.setGpa(cgpa);
        grades.setGpaPercentage(perc + "%");
        grades.setPerformance(calculatePerformance(gpa));
        return saveSemesterGrades(allMarks.getFirst(), grades);
    }

    public List<SemesterGrades> getGrades(Long studentId, int currentSemester) {
        return semesterGrades.findByStudentIdAndSemester(studentId, currentSemester);
    }

    public Grades getGrades(Long studentId) {
        Optional<Grades> grades = gradesRepo.findByStudentId(studentId);

        if(grades.isPresent()) {
            return grades.get();
        } else {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                    ErrorConstants.ErrorCode.STUDENT_NOT_FOUND.getCode(),
                    ErrorConstants.ErrorCode.STUDENT_NOT_FOUND.getMessage());
            throw new EntityNotFoundException(apiError, studentId);
        }
    }

    public static Pair<List<SemesterGrades>, Double> convertToSemesterGrades(Map<String, String> marks,
                                                                                              Long studentId, int currentSemester) {
        List<SemesterGrades> semesterGradesList = new ArrayList<>();
        AtomicReference<Double> gpa = new AtomicReference<>((double) 0);
        marks.forEach((subject, grade) -> {
            gpa.set(calculateGPA(grade, gpa.get()));
            SemesterGrades semesterGrades = new SemesterGrades();
            semesterGrades.setSemester(currentSemester);
            semesterGrades.setStudentId(studentId);
            semesterGrades.setSubject(subject);
            semesterGrades.setGrade(grade);
            semesterGradesList.add(semesterGrades);
        });
        double calculatedGpa = gpa.getPlain()/marks.size();
        calculatedGpa = Double.parseDouble(String.format("%.2f", calculatedGpa));
        return Pair.of(semesterGradesList, calculatedGpa);
    }

    public static String calculatePerformance(double gpa) {
        if (gpa >= 10) {
            return "Exemplary Performance of Outstanding Caliber";
        } else if (gpa >= 9.5) {
            return "Superb Performance Marked by Excellence";
        } else if (gpa >= 9) {
            return "Remarkable Performance with Distinction";
        } else if (gpa >= 8.5) {
            return "Very Strong Performance Reflecting Excellence";
        } else if (gpa >= 8) {
            return "Outstanding Performance with Great Merit";
        } else if (gpa >= 7) {
            return "Solid Performance with Good Standing";
        } else if (gpa >= 6) {
            return "Satisfactory Performance Demonstrating Competence";
        } else {
            return "Failed";
        }
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
            case "B" -> {
                double bPoints = 8.0;
                yield gpa + bPoints;
            }
            case "C" -> {
                double cPoints = 7.0;
                yield gpa + cPoints;
            }
            case "D" -> {
                double dPoints = 6.0;
                yield gpa + dPoints;
            }
            case "E" -> {
                double dPoints = 5.0;
                yield gpa + dPoints;
            }
            default -> gpa;
        };
    }
}
