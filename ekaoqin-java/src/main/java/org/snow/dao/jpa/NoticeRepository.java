package org.snow.dao.jpa;

import org.snow.model.business.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "notice", path = "/rest/notice")
public interface NoticeRepository extends PagingAndSortingRepository<Notice, Long> {
    @Query("select u from Notice u where u.title=:title")
    public Page<Notice> getNoticesByTitle(@Param("title") String title, Pageable pageable);
}
