package org.snow.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.snow.dao.jpa.NoticeRepository;
import org.snow.dao.mybatis.mapper.NoticeMapper;
import org.snow.model.business.Notice;
import org.snow.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private NoticeMapper noticeMapper;

    public Optional<Notice> getNoticeById(Long id) {
        return noticeRepository.findById(id);
    }
    public List<Notice> getAllNotices() {
        Iterable<Notice> geted = noticeRepository.findAll();
        List<Notice> list = new ArrayList<Notice>();
        geted.forEach(single ->{list.add(single);});
        return list;
    }
    @Transactional
    public Notice addNotice(Notice notice) {
        return noticeRepository.save(notice);
    }
    @Transactional
    public Notice updateNoticeById(Long id,Notice notice) {
        notice.setId(id);
        return noticeRepository.save(notice);
    }
    @Transactional
    public void deleteNoticeById(Long id) {
        noticeRepository.deleteById(id);
    }

    public PageInfo<Notice> getNoticesByTitle(String title,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Notice> noticesList = noticeMapper.selectNoticesByTitle(title);
        PageInfo<Notice> noticesPageInfo = new PageInfo<>(noticesList);

        return noticesPageInfo;
    }
}
