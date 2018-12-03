package org.snow.service.impl;


import ch.qos.logback.core.joran.util.beans.BeanUtil;
import org.snow.dao.jpa.*;
import org.snow.form.EntryLogRespond;
import org.snow.model.business.Building;
import org.snow.model.business.EntryLog;
import org.snow.model.business.Room;
import org.snow.model.business.Student;
import org.snow.service.EntryLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public List<EntryLogRespond> getAllEntryLogs() {

        Iterable<EntryLog> geted = entryLogRepository.findAll();
        List<EntryLogRespond> list = new ArrayList<EntryLogRespond>();
        geted.forEach(single -> {
            if (single.getIsDeleted() == null || single.getIsDeleted() == false) {
                EntryLogRespond entryLogRespond = new EntryLogRespond();
                BeanUtils.copyProperties(single, entryLogRespond);

                List<Student> student = studentRepository.findByFaceSysUserId(single.getFaceSysUserId());
                if (student.size() == 1) {
                    entryLogRespond.setStudentName(student.get(0).getName());
                    entryLogRespond.setClaxxName(claxxRepository.findById(student.get(0).getClassId()).get().getName());
                    entryLogRespond.setRoomName(roomRepository.findById(student.get(0).getRoomId()).get().getName());
                } else {
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
        if (entryLog.getFaceSysUserId().equals("") || entryLog.getFaceSysUserId() == null) {
            entryLog.setFaceSysUserId("陌生人");
        } else {
            entryLogRepository.save(entryLog);
            List<Student> students = studentRepository.findByFaceSysUserId(entryLog.getFaceSysUserId());
            if (students.size() > 0) {
                //查找学生所属建筑的入口出口的摄像头，判断学生是否进出。
                Optional<Room> room = roomRepository.findById(students.get(0).getRoomId());
                if (room.isPresent()) {
                    if (room.get().getBuildingId() != null) {
                        Optional<Building> building = buildingRepository.findById(room.get().getBuildingId());
                        if (building.isPresent()) {
                            if (building.get().getEntranceCamera().equals(entryLog.getCameraId())) {
                                students.get(0).setBackStatus(true);
                                studentRepository.save(students.get(0));
                            } else if (building.get().getExitCamera().equals(entryLog.getCameraId())) {
                                students.get(0).setBackStatus(false);
                                studentRepository.save(students.get(0));
                            }
                        }
                    }
                }
                //
                //if(entryLog.getCameraId().equals("宿舍入口")){
                //    students.get(0).setBackStatus(true);
                //}
                //if(entryLog.getCameraId().equals("宿舍出口")){
                //    students.get(0).setBackStatus(false);
                //}

            }
        }


        return true;
    }

}
