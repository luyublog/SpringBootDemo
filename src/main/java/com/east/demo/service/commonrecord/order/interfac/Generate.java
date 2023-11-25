package com.east.demo.service.commonrecord.order.interfac;

import com.east.demo.service.commonrecord.order.interfac.model.bo.NeededSavedInfo;
import com.east.demo.service.commonrecord.order.interfac.model.req.OrderRequest;

/**
 * 生成相关信息: 账单信息，统计信息
 *
 * @author: east
 * @date: 2023/11/23
 */
public interface Generate<T extends OrderRequest<?>, R extends NeededSavedInfo> {

    /**
     * 生成下单的落表数据
     * @param neededInfo 请求数据
     * @return result
     */
    public R generate(T neededInfo);

}
