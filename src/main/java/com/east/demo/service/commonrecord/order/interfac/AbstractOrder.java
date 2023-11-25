package com.east.demo.service.commonrecord.order.interfac;

import com.east.demo.service.commonrecord.order.interfac.req.OrderRequest;

/**
 * 下单行为虚函数
 *
 * @author: east
 * @date: 2023/11/24
 */
public abstract class AbstractOrder<T extends OrderRequest<?>> {
    protected final Check<T> check;
    protected final Generate generate;
    protected final Save save;
    protected final AfterOrder afterOrder;

    public AbstractOrder(Check<T> check, Generate generate, Save save, AfterOrder afterOrder) {
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
