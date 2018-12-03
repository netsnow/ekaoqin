package org.snow.dao.jpa;

import org.snow.model.business.Building;
import org.snow.model.business.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "building", path = "/building")
public interface BuildingRepository extends PagingAndSortingRepository<Building, Long> {

    Optional<Building> findByEntranceCamera(String entranceCamera);

    Optional<Building> findByExitCamera(String entranceCamera);
}
