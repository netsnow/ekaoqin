package org.snow.dao.jpa;

import org.snow.model.business.EntryLog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

@RepositoryRestResource(collectionResourceRel = "entryLog", path = "/entryLog")
public interface EntryLogRepository extends PagingAndSortingRepository<EntryLog, Long> {
    void deleteByCreateTimeLessThan(@Param("date") Date date);
}
