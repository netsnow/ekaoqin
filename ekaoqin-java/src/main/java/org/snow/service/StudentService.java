package org.snow.service;

import org.snow.form.StudentRespond;
import org.snow.model.business.Room;
import org.snow.model.business.Student;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    List<StudentRespond> getAllStudents();

    Boolean addStudent(Student student);

    Boolean updateStudentById(Long studentId, Student student);

    Boolean deleteStudentById(Long studentId);

    Boolean updateStatusByMq() throws IOException;
}
