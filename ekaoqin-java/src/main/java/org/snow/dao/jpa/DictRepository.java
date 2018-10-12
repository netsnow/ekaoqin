package org.snow.dao.jpa;


import org.snow.model.business.Dict;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "dict", path = "/dict")
public interface DictRepository extends PagingAndSortingRepository<Dict, Long> {
    List<Dict> findByType(@Param("type") String type);
    Dict findByIndex(@Param("index") String index);
}
