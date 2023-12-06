package com.east.demo.service.commonrecord.mybatis;

import cn.hutool.core.lang.Assert;
import com.east.demo.dao.LyEmployeeInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 数据库相关操作
 *
 * @author: east
 * @date: 2023/12/6
 */
@Service
@Slf4j
public class DatabaseOperation {
    private final LyEmployeeInfoDao lyEmployeeInfoDao;

    @Autowired
    public DatabaseOperation(LyEmployeeInfoDao lyEmployeeInfoDao) {
        Assert.isFalse(Objects.isNull(lyEmployeeInfoDao));
        this.lyEmployeeInfoDao = lyEmployeeInfoDao;
    }

    public void updateById(Long employeeId, String sexId) {
        lyEmployeeInfoDao.updateById(employeeId, sexId);
    }

    @Async(value = "myDefaultExecutorPool")
    public void asyncUpdateById(Long employeeId, String sexId) {
        lyEmployeeInfoDao.updateById(employeeId, sexId);
    }

    @Async(value = "myDefaultExecutorPool")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void asyncUpdateByIdWithTransactional(Long employeeId, String sexId) {
        lyEmployeeInfoDao.updateById(employeeId, sexId);
    }

}
