package org.snow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.snow.model.business.Dict;
import org.snow.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "字典RestController", tags = {"字典信息Restful接口"})
public class DictRestController {
    @Autowired
    private DictService dictService;

    @ApiOperation(value = "获取字典信息", notes = "根据类型获取字典信息")
    @RequestMapping(path = "/dict/{type}", method = RequestMethod.GET)
    public List<Dict> getDict(
        @ApiParam(name = "type", value = "类型", required = true) @PathVariable String type
    ) {
        return dictService.searchByType(type);
    }

}
