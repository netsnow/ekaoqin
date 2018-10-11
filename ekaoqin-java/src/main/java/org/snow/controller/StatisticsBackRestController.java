package org.snow.controller;

import org.snow.model.business.EntryLog;
import org.snow.model.business.StatisticsBack;
import org.snow.service.EntryLogService;
import org.snow.service.StatisticsBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        @PathVariable Date date
    ) {
        return statisticsBackService.getStatisticsBacksByDate(date);
    }


}
