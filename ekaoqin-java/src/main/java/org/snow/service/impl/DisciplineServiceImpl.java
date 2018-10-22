package org.snow.service.impl;


import org.snow.dao.jpa.*;
import org.snow.form.DisciplineRespond;
import org.snow.model.business.Discipline;
import org.snow.model.business.Student;
import org.snow.service.DisciplineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("disciplineService")
public class DisciplineServiceImpl implements DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClaxxRepository claxxRepository;

    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<DisciplineRespond> getAllDisciplines() {

        Iterable<Discipline> geted = disciplineRepository.findAll();
        List<DisciplineRespond> list = new ArrayList<DisciplineRespond>();
        geted.forEach(single -> {
            if (single.getIsDeleted() == null || single.getIsDeleted() == false) {
                DisciplineRespond disciplineRespond = new DisciplineRespond();
                BeanUtils.copyProperties(single,disciplineRespond);

                Optional<Student> student = studentRepository.findById(single.getStudentId());
                if(student.isPresent()){
                    disciplineRespond.setStudentName(student.get().getName());
                    disciplineRespond.setClaxxName(claxxRepository.findById(student.get().getClassId()).get().getName());
                    disciplineRespond.setRoomName(roomRepository.findById(student.get().getRoomId()).get().getName());
                }else{
                    disciplineRespond.setStudentName("陌生人");
                    disciplineRespond.setRoomName("未知");
                    disciplineRespond.setClaxxName("未知");
                }

                list.add(disciplineRespond);
            }
        });
        return list;
    }

    @Override
    @Transactional
    public Boolean addDiscipline(Discipline discipline) {
        disciplineRepository.save(discipline);
        return true;
    }

}
