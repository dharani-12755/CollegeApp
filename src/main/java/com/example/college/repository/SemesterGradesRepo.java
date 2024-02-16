package com.example.college.repository;

import com.example.college.model.SemesterGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterGradesRepo extends JpaRepository<SemesterGrades, Long> {
}
