package com.example.college.repository;

import com.example.college.constants.Department;
import com.example.college.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface StudentsRepo extends JpaRepository<Student, Long> {

    List<Student> findByUseCollegeBusIsTrue();

    List<Student> findByDepartmentEquals(Department department);

    List<Student> findStudentsByDepartmentAndYear(Department department, int year);

}
