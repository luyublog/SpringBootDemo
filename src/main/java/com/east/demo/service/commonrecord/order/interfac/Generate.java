package com.east.demo.service.commonrecord.order.interfac;

import java.util.function.Supplier;

/**
 * 生成相关信息: 账单所需信息
 *
 * @author: east
 * @date: 2023/11/23
 */
public interface Generate<T> {
    /**
     * 生成队列
     *
     * @param neededInfo 必要信息
     * @return result R
     */
    public void generateSequence(Supplier<T> neededInfo);


    /**
     * 获取收款信息
     *
     * @param neededInfo 必要信息
     * @return result R
     */
    public void generateRvcNo(Supplier<T> neededInfo);
}
