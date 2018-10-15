package org.snow.service.impl;


import org.snow.dao.jpa.ClaxxRepository;
import org.snow.dao.jpa.RoomRepository;
import org.snow.dao.jpa.StudentRepository;
import org.snow.form.StudentRespond;
import org.snow.model.business.Student;

import org.snow.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ClaxxRepository claxxRepository;

    @Override
    public List<StudentRespond> getAllStudents() {

        Iterable<Student> geted = studentRepository.findAll();
        List<StudentRespond> list = new ArrayList<StudentRespond>();
        geted.forEach(single -> {
            if (single.getIsDeleted() == null || single.getIsDeleted() == false) {
                StudentRespond studentRespond = new StudentRespond();
                BeanUtils.copyProperties(single,studentRespond);
                studentRespond.setClaxxName(claxxRepository.findById(single.getClassId()).get().getName());
                studentRespond.setRoomName(roomRepository.findById(single.getRoomId()).get().getName());
                list.add(studentRespond);
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
