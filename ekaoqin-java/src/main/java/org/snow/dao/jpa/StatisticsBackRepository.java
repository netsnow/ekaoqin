package org.snow.dao.jpa;

import org.snow.form.StatisticsBackClassRespond;
import org.snow.form.StatisticsBackRoomRespond;
import org.snow.model.business.StatisticsBack;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "statisticsBack", path = "/statisticsBack")
public interface StatisticsBackRepository extends PagingAndSortingRepository<StatisticsBack, Long> {
    @Query("delete from StatisticsBack u where u.date<:date")
    void deleteBeforeDate(@Param("date") Date date);

    @Query(
        value = "select u.class_name as className, count(1) as count , count(u.status = true or NULL) as backCount " +
            " from statistics_back u " +
            " where DATE_FORMAT(u.date,'%Y-%m-%d') = DATE_FORMAT(:date,'%Y-%m-%d' ) " +
            " group by u.class_name ", nativeQuery = true
    )
    List<StatisticsBackClassRespond> getClassByDate(@Param("date") Date date);

    @Query(
        value = "select u.room_name as roomName, count(1) as count , count(u.status = true or NULL) as backCount " +
            " from statistics_back u " +
            " where DATE_FORMAT(u.date,'%Y-%m-%d') = DATE_FORMAT(:date,'%Y-%m-%d' ) " +
            " group by u.room_name ", nativeQuery = true
    )
    List<StatisticsBackRoomRespond> getRoomByDate(@Param("date") Date date);
}
