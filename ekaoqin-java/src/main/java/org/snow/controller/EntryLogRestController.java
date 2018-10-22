package org.snow.controller;

import com.alibaba.fastjson.JSONObject;
import org.snow.form.EntryLogRespond;
import org.snow.form.MockTdRequest;
import org.snow.model.business.EntryLog;
import org.snow.model.business.Room;
import org.snow.service.EntryLogService;
import org.snow.service.RoomService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntryLogRestController {

    @Autowired
    private EntryLogService entryLogService;


    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RequestMapping(path = "/entryLog", method = RequestMethod.GET)
    public List<EntryLogRespond> getAllEntryLogs() {
        return entryLogService.getAllEntryLogs();
    }

    @RequestMapping(path = "/entryLog/{faceSysId}/{cameraId}", method = RequestMethod.GET)
    public Boolean addEntryLogMock(
        @PathVariable String faceSysId,
        @PathVariable String cameraId
    ) {
        MockTdRequest mockTdRequest = new MockTdRequest();
        mockTdRequest.setDataType("0");
        mockTdRequest.setCardId(faceSysId);
        mockTdRequest.setCameraInfo(cameraId);
        this.rabbitTemplate.convertAndSend("amq_sync_xhz", JSONObject.toJSONString(mockTdRequest));
        //return entryLogService.addEntryLog(entryLog);
        return true;
    }

}
