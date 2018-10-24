package org.snow.mq;

import com.alibaba.fastjson.JSONObject;
import org.snow.model.business.EntryLog;
import org.snow.service.EntryLogService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class MqLinstener {

    @Autowired
    private EntryLogService entryLogService;

    @RabbitListener(queues = "#{myQueue.name}")    //监听器监听指定的Queue
    public void addEntryLog(byte[] body) throws UnsupportedEncodingException {
        String entryMessage = new String(body, "UTF-8");
        System.out.println("Receive:" + entryMessage);
        JSONObject entryMessageJson = JSONObject.parseObject(entryMessage);
        //当取得数据的类型为普通抓拍记录（0）时
        if(entryMessageJson.containsKey("dataType")){
            if (entryMessageJson.get("dataType").equals(0)) {
                EntryLog entryLog = new EntryLog();
                if(entryMessageJson.containsKey("cardId")){
                    entryLog.setFaceSysUserId(entryMessageJson.get("cardId").toString());
                }else{
                    entryLog.setFaceSysUserId("");
                }
                if(entryMessageJson.containsKey("roadCaption")){
                    String result = java.net.URLDecoder.decode(entryMessageJson.get("roadCaption").toString(), "UTF-8");
                    entryLog.setCameraId(result);
                }else{
                    entryLog.setCameraId("");
                }

                entryLogService.addEntryLog(entryLog);
            }
        }else{
            System.out.println("no dataType");
        }


    }
}
