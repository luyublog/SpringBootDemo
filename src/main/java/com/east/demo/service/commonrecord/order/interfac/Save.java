package com.east.demo.service.commonrecord.order.interfac;

import java.util.function.Supplier;

/**
 * 保存下单相关信息
 *
 * @author: east
 * @date: 2023/11/23
 */
public interface Save<T> {
    /**
     * 保存下单数据
     *
     * @param savedInfo 相关信息
     */
    public void save(Supplier<T> savedInfo);
}
