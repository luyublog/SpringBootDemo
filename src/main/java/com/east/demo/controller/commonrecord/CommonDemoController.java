package com.east.demo.controller.commonrecord;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONObject;
import com.east.demo.common.annotation.SpeicalAspectAnnotation;
import com.east.demo.model.dto.base.resp.BaseResp;
import com.east.demo.model.dto.serialize.SerializeTestReq;
import com.east.demo.service.commonrecord.DemoService;
import com.east.demo.service.middle.kafka.KafkaProducerService;
import com.east.demo.service.util.async.AsyncDemoService;
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
    private final DemoService demoService;
    private final KafkaProducerService kafkaProducerService;
    private final AsyncDemoService asyncDemoService;

    @Autowired
    public CommonDemoController(DemoService demoService,
                                KafkaProducerService kafkaProducerService,
                                AsyncDemoService asyncDemoService) {
        Assert.isTrue(demoService != null);
        this.demoService = demoService;
        this.kafkaProducerService = kafkaProducerService;
        this.asyncDemoService = asyncDemoService;
    }


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

    /**
     * 生成kafka消息
     *
     * @return
     */
    @GetMapping(value = "/kafkaMessage")
    @ApiOperation(value = "生成kafka消息")
    public BaseResp<Object> kafkaMessage(@RequestParam(value = "message", required = false) String message,
                                         @RequestParam(value = "key", required = false) String key,
                                         @RequestParam(value = "partition", required = false) Integer partition) {
        kafkaProducerService.sendMessage(message, key, partition);
        return BaseResp.ok(null);
    }

    /**
     * 生成kafka消息
     *
     * @return
     */
    @GetMapping(value = "/asyncTransactionalDemo")
    @ApiOperation(value = "测试多线程下的事务")
    public BaseResp<Object> kafkaMessage() {
        asyncDemoService.asyncTransactionalUsage();
        return BaseResp.ok(null);
    }


}
