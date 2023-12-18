package com.east.demo.service.commonrecord.order.imp;

import com.east.demo.service.commonrecord.order.interfac.AfterOrder;
import com.east.demo.service.commonrecord.order.interfac.Check;
import com.east.demo.service.commonrecord.order.interfac.Generate;
import com.east.demo.service.commonrecord.order.interfac.Save;
import com.east.demo.service.commonrecord.order.interfac.model.bo.NeededSavedInfo;
import com.east.demo.service.commonrecord.order.interfac.model.bo.OrderInfo;
import com.east.demo.service.commonrecord.order.interfac.model.req.OrderRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * 简单的实现方法；直接在业务类中实现接口
 *
 * @author: east
 * @date: 2023/12/12
 */
@Service
// 实现了接口的类如果要注入需要指定为cglib代理，因为JDK代理只代理了接口，代理不了类
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SpecialOrder implements Check<OrderRequest<OrderInfo>>,
        Generate<OrderRequest<OrderInfo>, NeededSavedInfo>,
        Save<NeededSavedInfo>, AfterOrder<NeededSavedInfo> {
    @Override
    public void after(Supplier<NeededSavedInfo> savedInfo) {

    }

    @Override
    public void commonCheck(OrderRequest<OrderInfo> orderRequest) {

    }

    @Override
    public void specialCheck(OrderRequest<OrderInfo> orderRequest) {

    }

    @Override
    public NeededSavedInfo generate(OrderRequest<OrderInfo> neededInfo) {
        return null;
    }

    @Override
    public void save(NeededSavedInfo savedInfo) {

    }
}
