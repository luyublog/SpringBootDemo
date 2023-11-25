package com.east.demo.service.commonrecord.order.imp.after;

import com.east.demo.service.commonrecord.order.imp.model.bo.InnerNeededSavedInfo;
import com.east.demo.service.commonrecord.order.interfac.AfterOrder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * 内部下单后期处理
 *
 * @author: east
 * @date: 2023/11/24
 */
@Component
public class InnerAfterOrderAction implements AfterOrder<InnerNeededSavedInfo> {
    @Override
    public void after(Supplier<InnerNeededSavedInfo> savedInfo) {
        System.out.println("内部渠道下单落表后操作");
    }
}
