package com.east.demo.service.commonrecord.order.imp.save;

import com.east.demo.service.commonrecord.order.imp.model.bo.OrderInfo;
import com.east.demo.service.commonrecord.order.interfac.Save;

import java.util.function.Supplier;

/**
 * 内部渠道保存账单
 *
 * @author: east
 * @date: 2023/11/24
 */
public class InnerSaveOrder implements Save<OrderInfo> {
    @Override
    public void save(Supplier<OrderInfo> savedInfo) {

    }
}
