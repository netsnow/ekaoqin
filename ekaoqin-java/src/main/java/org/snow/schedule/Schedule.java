package org.snow.schedule;

import org.snow.service.StatisticsBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Schedule {

    @Autowired
    private StatisticsBackService statisticsBackService;

    //每分钟启动
    //@Scheduled(cron = "0 0/1 * * * ?")
    //每天22点启动
    @Scheduled(cron = "0 0 22 * * ?")
    public void addStatisticsBack(){
        statisticsBackService.addStatisticsBack();
        System.out.println("addStatisticsBack time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
