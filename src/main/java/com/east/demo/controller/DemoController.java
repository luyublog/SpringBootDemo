package com.east.demo.controller;

import com.east.demo.dto.LyUserOrganInfo;
import com.east.demo.service.DemoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@Api(tags = {"默认"})
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response) throws IOException {
        log.info("begin");
        demoService.generateResp(response);
    }

    @GetMapping("/demo")
    public List<LyUserOrganInfo> demo() {
        return demoService.demo();
    }

    @PostMapping("/demo")
    public String demo(@RequestBody String postData) {
        return postData;
    }


}
