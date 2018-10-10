package org.snow.dao.jpa;

import org.snow.model.business.Claxx;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "claxx", path = "/claxx")
public interface ClaxxRepository extends PagingAndSortingRepository<Claxx, Long> {
}
