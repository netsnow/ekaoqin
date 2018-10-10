package org.snow.dao.jpa;

import org.snow.model.business.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "student", path = "/student")
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
}
