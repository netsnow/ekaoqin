package org.snow.controller;

import org.snow.model.business.EntryLog;
import org.snow.model.business.Room;
import org.snow.service.EntryLogService;
import org.snow.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntryLogRestController {

    @Autowired
    private EntryLogService entryLogService;

    @RequestMapping(path = "/entryLog", method = RequestMethod.GET)
    public List<EntryLog> getAllEntryLogs() {
        return entryLogService.getAllEntryLogs();
    }

    @RequestMapping(path = "/entryLog/{faceSysId}/{cameraId}", method = RequestMethod.GET)
    public Boolean addEntryLog(
        @PathVariable String faceSysId,
        @PathVariable String cameraId
    ) {
        EntryLog entryLog = new EntryLog();
        entryLog.setFaceSysUserId(faceSysId);
        entryLog.setCameraId(cameraId);
        return entryLogService.addEntryLog(entryLog);
    }

}
