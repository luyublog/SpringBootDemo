package com.east.demo.service.commonrecord.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 配合gateway上相关逻辑的接口
 * @author: east
 * @date: 2023/9/15
 */

@Slf4j
@Service
public class GatewayDemoService {
    public String formData(String json, MultipartFile multipartFile) {
        log.info("json数据为:{}", json);
        return json;
    }
}
