package com.east.demo.service.commonrecord.order.interfac.req;

import com.east.demo.service.commonrecord.order.imp.model.bo.OrderInfo;

/**
 * @author: east
 * @date: 2023/11/23
 */
public interface OrderRequest<T extends OrderInfo> {
    /**
     * 将不同渠道报文转为T的统一格式数据（转为落表所需数据）
     *
     * @return T 统一数据
     */
    public T generateOrderInfo();
}
