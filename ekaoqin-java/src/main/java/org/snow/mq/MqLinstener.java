package org.snow.mq;

import com.alibaba.fastjson.JSONObject;
import org.snow.model.business.EntryLog;
import org.snow.service.EntryLogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqLinstener {

    @Autowired
    private EntryLogService entryLogService;

    @RabbitListener(queues = "amq_sync_xhz")    //监听器监听指定的Queue
    public void addEntryLog(String entryMessage) {
        System.out.println("Receive:" + entryMessage);
        JSONObject entryMessageJson = JSONObject.parseObject(entryMessage);
        //当取得数据的类型为普通抓拍记录（0）时
        if (entryMessageJson.get("dataType").equals("0")) {
            EntryLog entryLog = new EntryLog();
            entryLog.setFaceSysUserId(entryMessageJson.get("cardId").toString());
            entryLog.setCameraId(entryMessageJson.get("cameraInfo").toString());
            entryLogService.addEntryLog(entryLog);
        }

    }
}
