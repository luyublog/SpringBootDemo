package com.east.demo.service.commonrecord.order.imp.check;

import com.east.demo.service.commonrecord.order.imp.model.req.OuterOrderRequest;
import com.east.demo.service.commonrecord.order.interfac.Check;
import org.springframework.stereotype.Component;

/**
 * 外部渠道下单校验
 *
 * @author: east
 * @date: 2023/11/24
 */
@Component
public class OuterCheckOrderImp implements Check<OuterOrderRequest> {
    @Override
    public void commonCheck(OuterOrderRequest orderRequest) {

    }

    @Override
    public void specialCheck(OuterOrderRequest orderRequest) {

    }
}
