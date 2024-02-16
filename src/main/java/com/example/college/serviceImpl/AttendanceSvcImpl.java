package com.example.college.serviceImpl;

import com.example.college.model.Attendance;
import com.example.college.model.Student;
import com.example.college.repository.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendanceSvcImpl {

    @Autowired
    AttendanceRepo attendanceRepo;

    @Autowired
    StudentsSvcImpl studentsSvc;

    private static final double TOTAL_SEMESTER_DAYS = 120;

    public Attendance addAbsences(Long studentId, int noOfDaysAbsent) {
        Student student = studentsSvc.getStudent(studentId);
        Attendance attendance = new Attendance();
        attendance.setStudentId(studentId);
        attendance.setSemester(student.getCurrentSemester());
        Optional<Attendance> retrievedAttendance
                = attendanceRepo.findByStudentIdAndSemester(studentId, student.getCurrentSemester());
        if (retrievedAttendance.isPresent()) {
            int calculateNoOfAbsences = noOfDaysAbsent + retrievedAttendance.get().getNoOfAbsences();
            attendance.setNoOfAbsences(calculateNoOfAbsences);
            attendance.setAttendancePercentage(calculateAttendancePercentage(calculateNoOfAbsences));
        } else {
            attendance.setNoOfAbsences(noOfDaysAbsent);
            attendance.setAttendancePercentage(calculateAttendancePercentage(noOfDaysAbsent));
        }
        return attendanceRepo.save(attendance);
    }

    public Attendance getAttendance(Long studentId) {
        Student student = studentsSvc.getStudent(studentId);
        Optional<Attendance> retrievedAttendance = attendanceRepo.findById(studentId);
        if (retrievedAttendance.isPresent()) {
            return retrievedAttendance.get();
        } else {
            Attendance attendance = new Attendance();
            attendance.setStudentId(studentId);
            attendance.setSemester(student.getCurrentSemester());
            attendance.setNoOfAbsences(0);
            attendance.setAttendancePercentage(calculateAttendancePercentage(0));
            return attendanceRepo.save(attendance);
        }
    }

    public String calculateAttendancePercentage(int absences) {
        double daysAttended = TOTAL_SEMESTER_DAYS - (double) absences;
        return Math.round((daysAttended/TOTAL_SEMESTER_DAYS)*100) + "%";
    }
}
