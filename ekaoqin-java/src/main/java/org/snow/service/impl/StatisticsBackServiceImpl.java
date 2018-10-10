package org.snow.service.impl;


import org.snow.dao.jpa.StatisticsBackRepository;
import org.snow.model.business.EntryLog;
import org.snow.model.business.StatisticsBack;
import org.snow.service.StatisticsBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("entryLogService")
public class StatisticsBackServiceImpl implements StatisticsBackService {

    @Autowired
    private StatisticsBackRepository entryLogRepository;

    @Override
    public List<StatisticsBack> getAllStatisticsBacks() {

        Iterable<StatisticsBack> geted = entryLogRepository.findAll();
        List<StatisticsBack> list = new ArrayList<StatisticsBack>();
        geted.forEach(single -> {
            if (!single.getIsDeleted()) {
                list.add(single);
            }
        });
        return list;
    }

    @Override
    public Boolean addStatisticsBack(StatisticsBack statisticsBack) {
        entryLogRepository.save(statisticsBack);
        return true;
    }

}
