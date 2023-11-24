package com.east.demo.service.commonrecord.order.interfac;

import java.util.function.Supplier;

/**
 * 下单后处理
 *
 * @author: east
 * @date: 2023/11/23
 */
public interface AfterOrder<T> {
    /**
     * 保存下单数据后所需操作
     *
     * @param savedInfo 相关信息
     */
    public void after(Supplier<T> savedInfo);
}
