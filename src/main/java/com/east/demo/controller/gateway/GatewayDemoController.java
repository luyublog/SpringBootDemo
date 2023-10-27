package com.east.demo.controller.gateway;

import com.east.demo.service.commonrecord.gateway.GatewayDemoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 测试网关使用的相关接口
 * @author: east
 * @date: 2023/9/15
 */

@Slf4j
@Service
@Api(tags = {"测试网关使用的相关接口"})
@RequestMapping("/demo/gateway")
@RestController
public class GatewayDemoController {
    @Autowired
    GatewayDemoService gatewayDemoService;

    @PostMapping("/formData")
    public String formData(@RequestPart(value = "json") String json,
                           @RequestPart(value = "excel") MultipartFile multipartFile) {
        return gatewayDemoService.formData(json, multipartFile);
    }
}
