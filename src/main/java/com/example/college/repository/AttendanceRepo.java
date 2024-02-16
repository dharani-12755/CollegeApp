package com.example.college.repository;

import com.example.college.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByStudentIdAndSemester(Long studentId, int semester);
}
