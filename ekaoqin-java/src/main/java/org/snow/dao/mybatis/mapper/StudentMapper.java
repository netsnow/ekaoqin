package org.snow.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.snow.form.StudentRespond;
import org.snow.model.business.Claxx;
import org.snow.model.business.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> searchStudents(@Param("student") Student student);

    List<StudentRespond> FuzzySearchStudents(@Param("searchKey") String searchKey);

}
