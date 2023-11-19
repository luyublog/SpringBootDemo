package com.east.demo.controller.commonrecord.excel;

import com.east.demo.service.util.excel.ExcelDemoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * excel相关操作
 *
 * @author: east
 * @date: 2023/9/19
 */

@Slf4j
@Service
@Api(tags = {"excel相关操作"})
@RequestMapping("/demo/excel")
@RestController
public class ExcelDemoController {
    @Autowired
    private ExcelDemoService excelDemoService;

    @PostMapping("/getTemplate")
    public void getTemplate(@RequestBody String request, HttpServletResponse httpServletResponse) {
        excelDemoService.getTemplate(request, httpServletResponse);
    }
}
