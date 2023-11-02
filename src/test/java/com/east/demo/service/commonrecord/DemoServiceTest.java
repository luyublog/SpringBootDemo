package com.east.demo.service.commonrecord;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.east.demo.common.enums.StatusEnum;
import com.east.demo.pojo.dto.serialize.SerializeTestReq;
import org.junit.jupiter.api.Test;

class DemoServiceTest {
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

}