package org.snow.service.impl;


import ch.qos.logback.core.joran.util.beans.BeanUtil;
import org.snow.dao.jpa.ClaxxRepository;
import org.snow.dao.jpa.EntryLogRepository;
import org.snow.dao.jpa.RoomRepository;
import org.snow.dao.jpa.StudentRepository;
import org.snow.form.EntryLogRespond;
import org.snow.model.business.EntryLog;
import org.snow.model.business.Student;
import org.snow.service.EntryLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("entryLogService")
public class EntryLogServiceImpl implements EntryLogService {

    @Autowired
    private EntryLogRepository entryLogRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClaxxRepository claxxRepository;

    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<EntryLogRespond> getAllEntryLogs() {

        Iterable<EntryLog> geted = entryLogRepository.findAll();
        List<EntryLogRespond> list = new ArrayList<EntryLogRespond>();
        geted.forEach(single -> {
            if (single.getIsDeleted() == null || single.getIsDeleted() == false) {
                EntryLogRespond entryLogRespond = new EntryLogRespond();
                BeanUtils.copyProperties(single,entryLogRespond);

                List<Student> student = studentRepository.findByFaceSysUserId(single.getFaceSysUserId());
                if(student.size() == 1){
                    entryLogRespond.setStudentName(student.get(0).getName());
                    entryLogRespond.setClaxxName(claxxRepository.findById(student.get(0).getClassId()).get().getName());
                    entryLogRespond.setRoomName(roomRepository.findById(student.get(0).getRoomId()).get().getName());
                }else{
                    entryLogRespond.setStudentName("陌生人");
                    entryLogRespond.setRoomName("未知");
                    entryLogRespond.setClaxxName("未知");
                }

                list.add(entryLogRespond);
            }
        });
        return list;
    }

    @Override
    @Transactional
    public Boolean addEntryLog(EntryLog entryLog) {
        entryLogRepository.save(entryLog);
        List<Student> students = studentRepository.findByFaceSysUserId(entryLog.getFaceSysUserId());
        if(students.size() > 0){
            if(entryLog.getCameraId().equals("宿舍入口")){
                students.get(0).setBackStatus(true);
            }
            if(entryLog.getCameraId().equals("宿舍出口")){
                students.get(0).setBackStatus(false);
            }
            studentRepository.save(students.get(0));
        }

        return true;
    }

}
