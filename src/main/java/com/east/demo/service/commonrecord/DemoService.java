package com.east.demo.service.commonrecord;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.east.demo.common.enums.StatusEnum;
import com.east.demo.model.dto.base.resp.BaseResp;
import com.east.demo.model.dto.serialize.SerializeTestReq;
import com.east.demo.model.dto.test.LyEmployeeInfoDTO;
import com.east.demo.persist.mapper.custom.CustomLyEmployeeInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 * Created by east on 2023/8/6 18:38.
 */
@Service
@Slf4j
public class DemoService {

    @Autowired
    CustomLyEmployeeInfoMapper customLyEmployeeInfoMapper;

    public BaseResp<JSONObject> demo(JSONObject request) {
        log.info("this is exact service");
        LyEmployeeInfoDTO userModel = customLyEmployeeInfoMapper.getByName("Jack");
        log.info(userModel.toString());
        return BaseResp.ok(JSONUtil.parseObj(userModel));
    }

    /**
     * 🌠涉及到枚举的序列化
     *
     * @return SerializeTestReq
     */
    public BaseResp<SerializeTestReq> serializeEnum() {
        SerializeTestReq req = new SerializeTestReq();
        req.setUserId("userId1");
        req.setUserName("userName1");
        req.setStatus(StatusEnum.VALID);

        return BaseResp.ok(req);
    }


    public BaseResp<SerializeTestReq> deserializeEnum(SerializeTestReq req) {
        log.info("反序列化后结果为：{}", req.toString());
        return BaseResp.ok(req);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transactionalAnnotate() {
        customLyEmployeeInfoMapper.updateSexByEmployeeIdInt(100L);
        LyEmployeeInfoDTO info = customLyEmployeeInfoMapper.getByFullName("Steven", "King", "AD_PRES");
        log.info(info.toString());
//        throw new BaseException(ErrorEnum.FAIL);
    }
}

