package com.east.demo.service.commonrecord.order.imp.generate;

import com.east.demo.service.commonrecord.order.imp.model.bo.OrderInfo;
import com.east.demo.service.commonrecord.order.interfac.Generate;

import java.util.function.Supplier;

/**
 * 内部渠道生成账单相关信息
 *
 * @author: east
 * @date: 2023/11/24
 */
public class InnerGenerateOrder implements Generate<OrderInfo> {
    @Override
    public void generateSequence(Supplier<OrderInfo> neededInfo) {

    }

    @Override
    public void generateRvcNo(Supplier<OrderInfo> neededInfo) {

    }
}
