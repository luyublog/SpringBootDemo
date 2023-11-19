package com.east.demo.service.commonrecord;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.east.demo.common.enums.StatusEnum;
import com.east.demo.model.dto.serialize.SerializeTestReq;
import com.east.demo.model.dto.test.LyEmployeeInfoDTO;
import com.east.demo.persist.mapper.custom.CustomLyEmployeeInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DemoServiceTest {
    @Autowired
    private DemoService demoService;
    @Autowired
    private CustomLyEmployeeInfoMapper customLyEmployeeInfoMapper;

    @Test
    public void testSerialize() {
        SerializeTestReq req = new SerializeTestReq();
        req.setUserId("userId1");
        req.setUserName("userName1");
        req.setStatus(StatusEnum.VALID);


        JSONObject jsonObject = JSONUtil.parseObj(req);
        System.out.println(jsonObject);
    }

    @Test
    public void testDeserialize() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("userId", "userId1");
        jsonObject.set("userName", "userName1");
        jsonObject.set("status", "A");

        SerializeTestReq bean = jsonObject.toBean(SerializeTestReq.class);
        System.out.println(bean);
    }

    @Test
    void testTransactionalAnnotate() {
        demoService.transactionalAnnotate();
        log.info("update done");
        LyEmployeeInfoDTO info = customLyEmployeeInfoMapper.getByFullName("Steven", "King", "AD_PRES");
        log.info(info.toString());
    }
}