package org.snow.service.impl;


import org.snow.dao.jpa.RoomRepository;
import org.snow.dao.jpa.StudentRepository;
import org.snow.model.business.Room;
import org.snow.model.business.Student;
import org.snow.service.RoomService;
import org.snow.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {

        Iterable<Student> geted = studentRepository.findAll();
        List<Student> list = new ArrayList<Student>();
        geted.forEach(single -> {
            if (single.getIsDeleted() == null || single.getIsDeleted() == false) {
                list.add(single);
            }
        });
        return list;
    }

    @Override
    public Boolean addStudent(Student student) {
        studentRepository.save(student);
        return true;
    }

    @Override
    public Boolean updateStudentById(Long studentId, Student student) {
        student.setId(studentId);
        studentRepository.save(student);
        return true;
    }

    @Override
    public Boolean deleteStudentById(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        student.get().setIsDeleted(true);
        studentRepository.save(student.get());
        return true;
    }
}
