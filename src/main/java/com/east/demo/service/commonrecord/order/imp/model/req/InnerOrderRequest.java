package com.east.demo.service.commonrecord.order.imp.model.req;

import com.east.demo.service.commonrecord.order.imp.model.bo.OrderInfo;
import com.east.demo.service.commonrecord.order.interfac.req.OrderRequest;

/**
 * 内部渠道下单
 *
 * @author: east
 * @date: 2023/11/23
 */
public class InnerOrderRequest implements OrderRequest<OrderInfo> {

    /**
     * 生成账单信息
     *
     * @return 账单信息
     */
    @Override
    public OrderInfo generateOrderInfo() {
        return null;
    }
}
