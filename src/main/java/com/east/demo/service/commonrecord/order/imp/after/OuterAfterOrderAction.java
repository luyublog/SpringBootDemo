package com.east.demo.service.commonrecord.order.imp.after;

import com.east.demo.service.commonrecord.order.imp.model.bo.OuterNeededSavedInfo;
import com.east.demo.service.commonrecord.order.interfac.AfterOrder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * 外部渠道下单后处理
 *
 * @author: east
 * @date: 2023/11/24
 */
@Component
public class OuterAfterOrderAction implements AfterOrder<OuterNeededSavedInfo> {
    @Override
    public void after(Supplier<OuterNeededSavedInfo> savedInfo) {

    }
}
