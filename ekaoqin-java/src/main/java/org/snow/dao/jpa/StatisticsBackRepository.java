package org.snow.dao.jpa;

import org.snow.model.business.StatisticsBack;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

@RepositoryRestResource(collectionResourceRel = "statisticsBack", path = "/statisticsBack")
public interface StatisticsBackRepository extends PagingAndSortingRepository<StatisticsBack, Long> {
    @Query("delete from StatisticsBack u where u.date<:date")
    void deleteBeforeDate(@Param("date") Date date);

}
