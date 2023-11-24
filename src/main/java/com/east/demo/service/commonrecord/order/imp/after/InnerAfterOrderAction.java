package com.east.demo.service.commonrecord.order.imp.after;

import com.east.demo.service.commonrecord.order.imp.model.bo.OrderInfo;
import com.east.demo.service.commonrecord.order.interfac.AfterOrder;

import java.util.function.Supplier;

/**
 * 内部下单后期处理
 *
 * @author: east
 * @date: 2023/11/24
 */
public class InnerAfterOrderAction implements AfterOrder<OrderInfo> {
    @Override
    public void after(Supplier<OrderInfo> savedInfo) {

    }
}
