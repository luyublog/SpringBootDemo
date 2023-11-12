package com.east.demo.controller.commonrecord;

import cn.hutool.json.JSONObject;
import com.east.demo.common.annotation.SpeicalAspectAnnotation;
import com.east.demo.model.dto.base.resp.BaseResp;
import com.east.demo.model.dto.serialize.SerializeTestReq;
import com.east.demo.service.commonrecord.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 普通功能的记录demo
 *
 * @author: east
 * @date: 2023/9/13
 */
@Api(tags = "普通功能的记录")
@RestController
@RequestMapping(path = "/common")
public class CommonDemoController {
    @Autowired
    DemoService demoService;


    @PostMapping(value = "/demo")
    @ApiOperation(value = "测试demo")
    @SpeicalAspectAnnotation()
    public BaseResp<JSONObject> demo(@RequestBody JSONObject request) {
        return demoService.demo(request);
    }

    @GetMapping(value = "/serializeEnum")
    @ApiOperation(value = "测试序列化")
    public BaseResp<SerializeTestReq> serializeEnum() {
        return demoService.serializeEnum();
    }

    @PostMapping(value = "/deserializeEnum")
    @ApiOperation(value = "测试反序列化")
    public BaseResp<SerializeTestReq> deserializeEnum(@RequestBody SerializeTestReq req) {
        return demoService.deserializeEnum(req);
    }


}
