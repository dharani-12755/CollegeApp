package com.example.college.serviceImpl;

import com.example.college.constants.Department;
import com.example.college.errors.ApiError;
import com.example.college.errors.EntityNotFoundException;
import com.example.college.constants.ErrorConstants;
import com.example.college.model.Student;
import com.example.college.repository.StudentsRepo;
import com.example.college.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceSvcImpl {

    @Autowired
    StudentsRepo AttendanceRepo;

    public Student addStudent(Student student) {
        student.setId(IdGenerator.generateId());
        return AttendanceRepo.save(student);
    }

    public Student editStudent(Long id, Student student) {
        Optional<Student> retrievedStudent = AttendanceRepo.findById(id);
        if (retrievedStudent.isPresent()) {
            student.setId(id);
            return AttendanceRepo.save(student);
        } else {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                    ErrorConstants.ErrorCode.STUDENT_NOT_FOUND.getCode(),
                    ErrorConstants.ErrorCode.STUDENT_NOT_FOUND.getMessage());
            throw new EntityNotFoundException(apiError, id);
        }
    }

    public Student getStudent(Long id) {
        Optional<Student> student = AttendanceRepo.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                    ErrorConstants.ErrorCode.STUDENT_NOT_FOUND.getCode(),
                    ErrorConstants.ErrorCode.STUDENT_NOT_FOUND.getMessage());
            throw new EntityNotFoundException(apiError, id);
        }
    }

    public void deleteStudent(Long id) {
        Optional<Student> student = AttendanceRepo.findById(id);
        if (student.isPresent()) {
            AttendanceRepo.deleteById(id);
        } else {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                    ErrorConstants.ErrorCode.STUDENT_NOT_FOUND.getCode(),
                    ErrorConstants.ErrorCode.STUDENT_NOT_FOUND.getMessage());
            throw new EntityNotFoundException(apiError, id);
        }
    }

    public List<Student> getCollegeBusData() {
        return AttendanceRepo.findByUseCollegeBusIsTrue();
    }

    public List<Student> getStudentsByDept(String dept) {
        return AttendanceRepo.findByDepartmentEquals(Department.valueOf(dept));
    }
}
