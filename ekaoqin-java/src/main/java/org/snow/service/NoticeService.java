package org.snow.service;

import com.github.pagehelper.PageInfo;
import org.snow.model.business.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeService {
    public Optional<Notice> getNoticeById(Long id);

    public List<Notice> getAllNotices();

    Notice addNotice(Notice notice);

    Notice updateNoticeById(Long id,Notice notice);

    void deleteNoticeById(Long id);

    public PageInfo<Notice> getNoticesByTitle(String title, int pageNum,int pageSize);

}
