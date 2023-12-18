package com.east.demo.service.commonrecord.order.interfac;

import com.east.demo.service.commonrecord.order.interfac.model.bo.NeededSavedInfo;
import com.east.demo.service.commonrecord.order.interfac.model.req.OrderRequest;

/**
 * 下单行为虚函数
 * 整个下单有这几个关键点：
 * 1. 下单四个流程接口
 * 2. 1个账单通用基础bo（账单基础信息），1个落表信息基础对象（落表（不止一个表信息））
 *
 * @author: east
 * @date: 2023/11/24
 */
public abstract class AbstractOrder<T extends OrderRequest<?>, R extends NeededSavedInfo> {
    //    直接用通配符会报错
//    protected Generate<T, ? extends NeededSavedInfo> generate;
//    protected Save<? extends NeededSavedInfo> save;
//    protected AfterOrder<? extends NeededSavedInfo> afterOrder;
    protected Check<T> check;
    protected Generate<T, R> generate;
    protected Save<R> save;
    protected AfterOrder<R> afterOrder;

    public AbstractOrder(Check<T> check,
                         Generate<T, R> generate,
                         Save<R> save,
                         AfterOrder<R> afterOrder) {
        this.check = check;
        this.generate = generate;
        this.save = save;
        this.afterOrder = afterOrder;
    }

    /**
     * 下单
     */
    public abstract void order(T orderRequest);
}
