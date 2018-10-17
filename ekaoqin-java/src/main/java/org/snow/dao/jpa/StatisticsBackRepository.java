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
    void deleteByDateLessThan(@Param("date") Date date);
}
