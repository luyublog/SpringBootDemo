package com.east.demo.controller;

import com.east.demo.dto.LyUserOrganInfo;
import com.east.demo.service.DemoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(tags = {"默认"})
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping("/demo")
    public List<LyUserOrganInfo> demo() {
        return demoService.demo();
    }
}
