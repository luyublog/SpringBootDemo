package com.east.demo.service.commonrecord.batch.db;

import com.east.demo.persist.entity.base.LyOrderInfo;
import com.east.demo.service.commonrecord.order.imp.SpecialOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DbBatchOperationTest {
    @Autowired
    SpecialOrder specialOrder;
    @Autowired
    DbBatchOperation dbBatchOperation;

    @Test
    void batchInsert() {
        List<LyOrderInfo> lyOrderInfos = specialOrder.generateInfoList(3);
        dbBatchOperation.batchInsert(lyOrderInfos);
    }

    @Test
    void batchUpdate() {
    }
}