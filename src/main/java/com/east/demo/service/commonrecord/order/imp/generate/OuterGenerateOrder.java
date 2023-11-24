package com.east.demo.service.commonrecord.order.imp.generate;

import com.east.demo.service.commonrecord.order.imp.model.bo.OrderInfo;
import com.east.demo.service.commonrecord.order.interfac.Generate;

import java.util.function.Supplier;

/**
 * 外部渠道生成账单信息
 *
 * @author: east
 * @date: 2023/11/24
 */
public class OuterGenerateOrder implements Generate<OrderInfo> {
    @Override
    public void generateSequence(Supplier<OrderInfo> neededInfo) {

    }

    @Override
    public void generateRvcNo(Supplier<OrderInfo> neededInfo) {

    }
}
