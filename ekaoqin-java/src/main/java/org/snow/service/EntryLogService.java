package org.snow.service;

import org.snow.model.business.EntryLog;

import java.util.List;

public interface EntryLogService {

    List<EntryLog> getAllEntryLogs();

    Boolean addEntryLog(EntryLog entryLog);

}
