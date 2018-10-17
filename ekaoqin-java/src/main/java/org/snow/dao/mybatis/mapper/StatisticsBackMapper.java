package org.snow.dao.mybatis.mapper;

import org.snow.form.StatisticsBackClassRespond;
import org.snow.form.StatisticsBackRoomRespond;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StatisticsBackMapper {
    List<StatisticsBackClassRespond> getClassByDate(@Param("date") Date date);

    List<StatisticsBackRoomRespond> getRoomByDate(@Param("date") Date date);
}
