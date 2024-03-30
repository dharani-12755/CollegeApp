package com.example.college.repository;

import com.example.college.model.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradesRepo extends JpaRepository<Grades, Long> {

    Optional<Grades> findByStudentId(Long studentId);
}
