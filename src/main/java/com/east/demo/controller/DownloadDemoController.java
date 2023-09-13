package com.east.demo.controller;

import com.east.demo.service.download.DownloadDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/data")
    public ResponseEntity<byte[]> getDataWithExcel() {
        return downloadDemoService.getDataWithExcel();
    }
}
