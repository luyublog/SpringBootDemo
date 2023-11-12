package com.east.demo.service.commonrecord;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.east.demo.common.enums.StatusEnum;
import com.east.demo.persist.entity.LyEmployeeInfo;
import com.east.demo.persist.mapper.LyEmployeeInfoMapper;
import com.east.demo.pojo.dto.base.resp.BaseResp;
import com.east.demo.pojo.dto.serialize.SerializeTestReq;
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
    LyEmployeeInfoMapper lyEmployeeInfoMapper;

    public BaseResp<JSONObject> demo(JSONObject request) {
        log.info("this is exact service");
        LyEmployeeInfo userModel = lyEmployeeInfoMapper.getByName("Jack");
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
        lyEmployeeInfoMapper.updateSexByEmployeeIdInt(100L);
        LyEmployeeInfo info = lyEmployeeInfoMapper.getByFullName("Steven", "King", "AD_PRES");
        log.info(info.toString());
//        throw new BaseException(ErrorEnum.FAIL);
    }
}

