package org.snow.service;

import org.snow.model.business.StatisticsBack;

import java.util.List;

public interface StatisticsBackService {

    List<StatisticsBack> getAllStatisticsBacks();

    Boolean addStatisticsBack(StatisticsBack statisticsBack);

}
