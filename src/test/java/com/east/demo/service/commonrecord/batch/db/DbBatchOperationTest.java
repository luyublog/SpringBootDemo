package com.east.demo.service.commonrecord.batch.db;

import com.east.demo.persist.entity.base.LyOrderInfo;
import com.east.demo.persist.mapper.custom.CustomLyOrderInfoMapper;
import com.east.demo.service.commonrecord.order.imp.SpecialOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class DbBatchOperationTest {
    @Autowired
    SpecialOrder specialOrder;
    @Autowired
    DbBatchOperation dbBatchOperation;
    @Autowired
    CustomLyOrderInfoMapper customLyOrderInfoMapper;

    @Test
    void batchInsert() {
        log.info("开始生成数据");
        List<LyOrderInfo> lyOrderInfos = specialOrder.generateInfoList(1000);
        log.info("开始插入数据");
        dbBatchOperation.batchInsert(lyOrderInfos);
    }

    @Test
    void batchUpdate() {
        List<LyOrderInfo> all = customLyOrderInfoMapper.getAll(1000);
        dbBatchOperation.batchUpdate(all);
    }
}