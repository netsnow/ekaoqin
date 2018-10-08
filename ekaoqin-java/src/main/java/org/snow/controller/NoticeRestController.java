package org.snow.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.snow.model.business.Notice;
import org.snow.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value="通知RestController",tags={"通知信息Restful接口"})
public class NoticeRestController {

    @Autowired
    private NoticeService noticeService;

    @ApiOperation(value="获取全部通知信息", notes="获取全部通知信息")
    @RequestMapping(path = "/notice", method = RequestMethod.GET)
    public List<Notice> getAllNotices() {
        return noticeService.getAllNotices();
    }

    @ApiOperation(value="根据id获取一条通知信息", notes="根据url的id来获取一条通知信息")
    @RequestMapping(path = "/notice/{id}", method = RequestMethod.GET)
    public Optional<Notice> getNoticeById(
        @ApiParam(name = "id", value = "通知ID", required = true) @PathVariable Long id) {
        return noticeService.getNoticeById(id);
    }

    @ApiOperation(value="新增通知信息", notes="新增通知信息（不用填写id）")
    @RequestMapping(path = "/notice", method = RequestMethod.POST)
    public Notice addNotice(@RequestBody Notice notice) {
        return noticeService.addNotice(notice);
    }

    @ApiOperation(value="修改通知信息", notes="修改通知信息")
    @RequestMapping(path = "/notice/{id}", method = RequestMethod.PUT)
    public Notice updateNotice(
        @ApiParam(name = "id", value = "通知ID", required = true) @PathVariable Long id,
        @RequestBody Notice notice) {
        return noticeService.updateNoticeById(id,notice);
    }

    @ApiOperation(value="删除通知信息", notes="删除通知信息")
    @RequestMapping(path = "/notice/{id}", method = RequestMethod.DELETE)
    public void deleteNoticeById(
        @ApiParam(name = "id", value = "通知ID", required = true) @PathVariable Long id) {
        noticeService.deleteNoticeById(id);
    }
    @ApiOperation(value="查询通知信息", notes="查询通知信息")
    @RequestMapping(path = "/notice/search", method = RequestMethod.GET)
    public PageInfo<Notice> getNoticesByTitle(
        @ApiParam(name = "title", value = "通知标题", required = true) @RequestParam(value = "title",required = true) String title,
        @ApiParam(name = "page", value = "查询页码", required = true) @RequestParam(value = "page",required = false,defaultValue = "1") int page,
        @ApiParam(name = "size", value = "每页行数", required = true) @RequestParam(value = "size",required = false,defaultValue = "20") int size) {

        return noticeService.getNoticesByTitle(title,page,size);
    }
}
