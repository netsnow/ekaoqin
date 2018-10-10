package org.snow.dao.jpa;

import org.snow.model.business.StatisticsBack;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "statisticsBack", path = "/statisticsBack")
public interface StatisitcsBackRepository extends PagingAndSortingRepository<StatisticsBack, Long> {
}
