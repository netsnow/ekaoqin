package org.snow.service.impl;


import org.snow.dao.jpa.EntryLogRepository;
import org.snow.model.business.EntryLog;
import org.snow.service.EntryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("entryLogService")
public class EntryLogServiceImpl implements EntryLogService {

    @Autowired
    private EntryLogRepository entryLogRepository;

    @Override
    public List<EntryLog> getAllEntryLogs() {

        Iterable<EntryLog> geted = entryLogRepository.findAll();
        List<EntryLog> list = new ArrayList<EntryLog>();
        geted.forEach(single -> {
            if (!single.getIsDeleted()) {
                list.add(single);
            }
        });
        return list;
    }

    @Override
    public Boolean addEntryLog(EntryLog entryLog) {
        entryLogRepository.save(entryLog);
        return true;
    }

}
