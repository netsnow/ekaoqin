package org.snow.service;

import org.snow.form.EntryLogRespond;
import org.snow.model.business.EntryLog;

import java.util.List;

public interface EntryLogService {

    List<EntryLogRespond> getAllEntryLogs();

    Boolean addEntryLog(EntryLog entryLog);

}
