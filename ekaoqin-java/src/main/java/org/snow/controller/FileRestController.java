package org.snow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.snow.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value="文件RestController",tags={"文件系统Restful接口"})
public class FileRestController {
    private final StorageService storageService;

    @Autowired
    public FileRestController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ApiOperation(value="取得文件资源", notes="通过rest接口取得文件资源")
    @RequestMapping(path = "/file/{filename}", method = RequestMethod.GET)
    public Resource getFile(
        @ApiParam(name = "filename", value = "文件名", required = true) @PathVariable String filename){
        return storageService.loadAsResource(filename);
    }

    @ApiOperation(value="提交文件资源", notes="通过rest接口提交文件资源，前端需要通过form提交")
    @RequestMapping(path = "/file", method = RequestMethod.POST)
    public String uploadFile(
        @ApiParam(name = "file", value = "文件体", required = true) @RequestParam("file") MultipartFile file,
        @ApiParam(name = "filename", value = "文件名", required = true) @RequestParam("filename") String filename ,
        HttpServletRequest request){
        storageService.store(file,filename);
        return filename;
    }

}
