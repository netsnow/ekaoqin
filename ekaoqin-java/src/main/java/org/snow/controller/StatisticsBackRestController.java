package org.snow.controller;

import org.snow.form.StatisticsBackClassRespond;
import org.snow.form.StatisticsBackRoomRespond;
import org.snow.model.business.EntryLog;
import org.snow.model.business.StatisticsBack;
import org.snow.service.EntryLogService;
import org.snow.service.StatisticsBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class StatisticsBackRestController {

    @Autowired
    private StatisticsBackService statisticsBackService;

    @RequestMapping(path = "/statisticsBack", method = RequestMethod.GET)
    public List<StatisticsBack> getAllStatisticsBacks() {
        return statisticsBackService.getAllStatisticsBacks();
    }

    @RequestMapping(path = "/statisticsBack/{date}", method = RequestMethod.GET)
    public List<StatisticsBack> getStatisticsBacksByDate(
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        return statisticsBackService.getStatisticsBacksByDate(date);
    }

    @RequestMapping(path = "/statisticsBack/{date}/claxx", method = RequestMethod.GET)
    public List<StatisticsBackClassRespond> getStatisticsBacksClaxxByDate(
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        return statisticsBackService.getStatisticsBacksClassByDate(date);
    }

    @RequestMapping(path = "/statisticsBack/{date}/room", method = RequestMethod.GET)
    public List<StatisticsBackRoomRespond> getStatisticsBacksRoomByDate(
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        return statisticsBackService.getStatisticsBacksRoomByDate(date);
    }

    @RequestMapping(path = "/statisticsBack/search", method = RequestMethod.POST)
    public List<StatisticsBack> searchStatisticsBacks(
        @RequestBody StatisticsBack statisticsBack
    ) {
        return statisticsBackService.searchStatisticsBacks(statisticsBack);
    }
}
