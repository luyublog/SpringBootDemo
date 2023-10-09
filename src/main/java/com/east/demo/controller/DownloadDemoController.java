package com.east.demo.controller;

import com.east.demo.service.download.DownloadDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 涉及下载相关demo
 * @author: east
 * @date: 2023/9/13
 */
@RestController
@RequestMapping(path = "/download")
public class DownloadDemoController {
    @Autowired
    DownloadDemoService downloadDemoService;

    @GetMapping(value = "/getJsonDataWithExcel")
    @Deprecated
    // 不好用 原因： 1.boundary规则 2.前端处理复杂
    public ResponseEntity<byte[]> getJsonDataWithExcel() {
        return downloadDemoService.getJsonDataWithExcel();
    }

    @GetMapping("/downloadFileOrGetJson")
    public void downloadFileOrGetJson(@RequestParam("fileType") String fileType, HttpServletResponse response) throws IOException {
        downloadDemoService.downloadFileOrGetJson(fileType, response);
    }
}
