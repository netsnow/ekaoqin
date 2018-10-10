package org.snow.service;

import org.snow.model.business.Room;
import org.snow.model.business.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Boolean addStudent(Student student);

    Boolean updateStudentById(Long studentId, Student student);

    Boolean deleteStudentById(Long studentId);
}
