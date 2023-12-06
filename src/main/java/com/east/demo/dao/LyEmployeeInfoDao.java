package com.east.demo.dao;

import cn.hutool.core.lang.Assert;
import com.east.demo.common.exception.BaseException;
import com.east.demo.common.exception.ErrorEnum;
import com.east.demo.persist.mapper.custom.CustomLyEmployeeInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 表数据操作类
 *
 * @author: east
 * @date: 2023/12/6
 */
@Service  // note 这里作为service？？
@Slf4j
public class LyEmployeeInfoDao {
    private final CustomLyEmployeeInfoMapper customLyEmployeeInfoMapper;

    @Autowired
    public LyEmployeeInfoDao(CustomLyEmployeeInfoMapper customLyEmployeeInfoMapper) {
        Assert.isFalse(Objects.isNull(customLyEmployeeInfoMapper));
        this.customLyEmployeeInfoMapper = customLyEmployeeInfoMapper;
    }

    public void updateById(Long employeeId, String sexId) {
        Assert.isTrue(customLyEmployeeInfoMapper.updateSexByEmployeeId(employeeId, sexId) > 0,
                () -> new BaseException(ErrorEnum.FAIL, () -> "更新失败"));
        log.info(String.format("id: %s, sexId: %s 更新成功", employeeId, sexId));
    }
}
