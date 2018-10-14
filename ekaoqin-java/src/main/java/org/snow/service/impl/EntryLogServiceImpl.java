package org.snow.service.impl;


import org.snow.dao.jpa.EntryLogRepository;
import org.snow.dao.jpa.StudentRepository;
import org.snow.model.business.EntryLog;
import org.snow.model.business.Student;
import org.snow.service.EntryLogService;
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

    @Override
    public List<EntryLog> getAllEntryLogs() {

        Iterable<EntryLog> geted = entryLogRepository.findAll();
        List<EntryLog> list = new ArrayList<EntryLog>();
        geted.forEach(single -> {
            if (single.getIsDeleted() == null || single.getIsDeleted() == false) {
                list.add(single);
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
            if(entryLog.getCameraId().equals("in001")){
                students.get(0).setBackStatus(true);
            }
            if(entryLog.getCameraId().equals("out001")){
                students.get(0).setBackStatus(false);
            }
            studentRepository.save(students.get(0));
        }

        return true;
    }

}
