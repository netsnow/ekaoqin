package org.snow.dao.jpa;

import org.snow.model.security.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "/user")
public interface UsersRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(@Param("username") String username);
}
