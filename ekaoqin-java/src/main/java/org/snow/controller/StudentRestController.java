package org.snow.controller;

import org.snow.form.StudentRespond;
import org.snow.model.business.Room;
import org.snow.model.business.Student;
import org.snow.service.RoomService;
import org.snow.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(path = "/student", method = RequestMethod.GET)
    public List<StudentRespond> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(path = "/student", method = RequestMethod.POST)
    public Boolean addStudent(
        @RequestBody Student student
    ) {
        return studentService.addStudent(student);
    }

    @RequestMapping(path = "/student/{id}", method = RequestMethod.PUT)
    public Boolean updateStudentById(
        @PathVariable Long id,
        @RequestBody Student student
    ) {
        return studentService.updateStudentById(id,student);
    }

    @RequestMapping(path = "/student/{id}", method = RequestMethod.DELETE)
    public Boolean deleteStudentById(
        @PathVariable Long id
    ) {
        return studentService.deleteStudentById(id);
    }

    @RequestMapping(path = "/student/search", method = RequestMethod.POST)
    public List<StudentRespond> searchStudents(
        @RequestBody Student student
    ) {
        return studentService.searchStudents(student);
    }

    @RequestMapping(path = "/student/fuzzySearch", method = RequestMethod.GET)
    public List<StudentRespond> searchFuzzyStudents(
        @RequestParam(value = "key",required = false) String key
    ) {
        return studentService.searchFuzzyStudents(key);
    }

}
