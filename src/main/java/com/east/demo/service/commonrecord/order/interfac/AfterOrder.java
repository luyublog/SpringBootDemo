package com.east.demo.service.commonrecord.order.interfac;

import com.east.demo.service.commonrecord.order.interfac.model.bo.NeededSavedInfo;

import java.util.function.Supplier;

/**
 * 下单后处理
 *
 * @author: east
 * @date: 2023/11/23
 */
public interface AfterOrder<T extends NeededSavedInfo> {
    /**
     * 保存下单数据后所需操作
     * Supplier: 后续不一定使用，Supplier可减少真实调用
     *
     * @param savedInfo 相关信息
     */
    public void after(Supplier<T> savedInfo);
}
