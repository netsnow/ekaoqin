package org.snow.service.impl;


import org.snow.dao.jpa.ClaxxRepository;
import org.snow.dao.jpa.RoomRepository;
import org.snow.dao.jpa.StatisticsBackRepository;
import org.snow.model.business.EntryLog;
import org.snow.model.business.StatisticsBack;
import org.snow.model.business.Student;
import org.snow.service.StatisticsBackService;
import org.snow.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("statisticsBackService")
public class StatisticsBackServiceImpl implements StatisticsBackService {

    @Autowired
    private StatisticsBackRepository statisticsBackRepository;

    @Autowired
    private ClaxxRepository claxxRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private StudentService studentService;

    @Override
    public List<StatisticsBack> getAllStatisticsBacks() {

        Iterable<StatisticsBack> geted = statisticsBackRepository.findAll();
        List<StatisticsBack> list = new ArrayList<StatisticsBack>();
        geted.forEach(single -> {
            if (single.getIsDeleted() == null || single.getIsDeleted() == false) {
                list.add(single);
            }
        });
        return list;
    }

    @Override
    public List<StatisticsBack> getStatisticsBacksByDate(Date date) {

        List<StatisticsBack> statisticsBacks = getAllStatisticsBacks();
        List<StatisticsBack> outList = new ArrayList<StatisticsBack>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < statisticsBacks.size(); i++) {
            if (sdf.format(statisticsBacks.get(i).getDate()) == sdf.format(date)) {
                outList.add(statisticsBacks.get(i));
            }
        }
        return outList;
    }

    @Override
    @Transactional
    public Boolean addStatisticsBack() {
        //删除一个月前的数据
        Date now = new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.MONTH, -3);//日期减3个月
        Date lastMonth = rightNow.getTime();
        //statisticsBackRepository.deleteByDate(lastMonth);

        //添加当日数据
        List<Student> students = studentService.getAllStudents();

        for (int i = 0; i < students.size(); i++) {
            StatisticsBack statisticsBack = new StatisticsBack();
            statisticsBack.setUserId(students.get(i).getId());
            statisticsBack.setDate(now);
            statisticsBack.setClassName(claxxRepository.findById(students.get(i).getClassId()).get().getName());
            statisticsBack.setRoomName(roomRepository.findById(students.get(i).getRoomId()).get().getName());
            statisticsBack.setUserName(students.get(i).getName());
            statisticsBack.setStatus(students.get(i).getBackStatus());
            statisticsBackRepository.save(statisticsBack);
        }
        return true;
    }

}