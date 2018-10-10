package org.snow.dao.jpa;

import org.snow.model.business.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "room", path = "/room")
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
}
