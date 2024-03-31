package com.example.college.repository;

import com.example.college.model.Grades;
import com.example.college.model.SemesterGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SemesterGradesRepo extends JpaRepository<SemesterGrades, Long> {

    List<SemesterGrades> findByStudentIdAndSemester(Long studentId, int semester);

    List<SemesterGrades> findByStudentId(Long studentId);
}
