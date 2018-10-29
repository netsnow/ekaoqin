package org.snow.service;

import org.snow.form.StatisticsBackClassRespond;
import org.snow.form.StatisticsBackRoomRespond;
import org.snow.model.business.StatisticsBack;

import java.util.Date;
import java.util.List;

public interface StatisticsBackService {

    List<StatisticsBack> getAllStatisticsBacks();

    List<StatisticsBack> getStatisticsBacksByDate(Date date);

    List<StatisticsBackClassRespond> getStatisticsBacksClassByDate(Date date);

    List<StatisticsBackRoomRespond> getStatisticsBacksRoomByDate(Date date);

    Boolean addStatisticsBack();

    List<StatisticsBack> searchStatisticsBacks(StatisticsBack statisticsBack);

    Boolean switchStatus(Long id);

}
