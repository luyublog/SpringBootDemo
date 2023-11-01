package com.east.demo.service.commonrecord;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.east.demo.dto.base.resp.BaseResp;
import com.east.demo.persist.entity.LyUserInfo;
import com.east.demo.persist.mapper.LyUserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Created by east on 2023/8/6 18:38.
 */
@Service
@Slf4j
public class DemoService {

    @Autowired
    LyUserInfoMapper lyUserInfoMapper;

    public BaseResp<JSONObject> demo(JSONObject request) {
        log.info("this is exact service");
        LyUserInfo userModel = lyUserInfoMapper.getByName("Jack");
        log.info(userModel.toString());
        return BaseResp.ok(JSONUtil.parseObj(userModel));
    }


}

