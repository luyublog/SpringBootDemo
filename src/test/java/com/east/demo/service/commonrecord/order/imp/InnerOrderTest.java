package com.east.demo.service.commonrecord.order.imp;

import com.east.demo.service.commonrecord.order.imp.model.req.InnerOrderRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InnerOrderTest {
    @Autowired
    InnerOrder innerOrder;

    @Test
    void order() {
        InnerOrderRequest innerOrderRequest = new InnerOrderRequest();
        innerOrder.order(innerOrderRequest);

    }
}