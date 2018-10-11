package org.snow.service;

import org.snow.model.business.StatisticsBack;

import java.util.Date;
import java.util.List;

public interface StatisticsBackService {

    List<StatisticsBack> getAllStatisticsBacks();

    List<StatisticsBack> getStatisticsBacksByDate(Date date);

    Boolean addStatisticsBack();

}
