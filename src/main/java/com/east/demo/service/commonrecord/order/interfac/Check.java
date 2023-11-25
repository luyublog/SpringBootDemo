package com.east.demo.service.commonrecord.order.interfac;

import com.east.demo.service.commonrecord.order.imp.model.bo.OrderInfo;
import com.east.demo.service.commonrecord.order.interfac.req.OrderRequest;

/**
 * 预检查
 *
 * @author: east
 * @date: 2023/11/23
 */
public interface Check<T extends OrderRequest<? extends OrderInfo>> {
    /**
     * 基本检查： 常识性逻辑（时间顺序）
     *
     * @param orderRequest req
     */
    public void commonCheck(T orderRequest);

    /**
     * 特殊检查： 按不同下单模式来
     *
     * @param orderRequest req
     */
    public void specialCheck(T orderRequest);
}
