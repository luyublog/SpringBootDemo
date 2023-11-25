package com.east.demo.service.commonrecord.order.interfac;

import com.east.demo.service.commonrecord.order.interfac.model.bo.NeededSavedInfo;

/**
 * 保存下单相关信息
 *
 * @author: east
 * @date: 2023/11/23
 */
public interface Save<T extends NeededSavedInfo> {
    /**
     * 保存下单数据
     *
     * @param savedInfo 相关信息:账单表，统计表
     */
    public void save(T savedInfo);
}
