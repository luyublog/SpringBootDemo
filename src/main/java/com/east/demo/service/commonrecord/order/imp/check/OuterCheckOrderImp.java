package com.east.demo.service.commonrecord.order.imp.check;

import com.east.demo.service.commonrecord.order.imp.model.req.OuterOrderRequest;
import com.east.demo.service.commonrecord.order.interfac.Check;
import com.east.demo.service.commonrecord.order.interfac.req.OrderRequest;

/**
 * 外部渠道下单校验
 *
 * @author: east
 * @date: 2023/11/24
 */
public class OuterCheckOrderImp implements Check<OuterOrderRequest> {
    @Override
    public void commonCheck(OrderRequest<OuterOrderRequest> orderRequest) {

    }

    @Override
    public void specialCheck(OrderRequest<OuterOrderRequest> orderRequest) {

    }
}
