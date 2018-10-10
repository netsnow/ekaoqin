package org.snow.dao.jpa;

import org.snow.model.business.EntryLog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "entryLog", path = "/entryLog")
public interface EntryLogRepository extends PagingAndSortingRepository<EntryLog, Long> {
}
