package org.snow.dao.jpa;

import org.snow.model.business.Discipline;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "discipline", path = "/discipline")
public interface DisciplineRepository extends PagingAndSortingRepository<Discipline, Long> {
}
