package org.snow.dao.mybatis.mapper;

import org.snow.model.business.Notice;

import java.util.List;

public interface  NoticeMapper {
    List<Notice> selectNoticesByTitle(String title);

}
