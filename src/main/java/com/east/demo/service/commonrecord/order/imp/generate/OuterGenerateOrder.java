package com.east.demo.service.commonrecord.order.imp.generate;

import com.east.demo.service.commonrecord.order.imp.model.bo.OuterNeededSavedInfo;
import com.east.demo.service.commonrecord.order.imp.model.req.OuterOrderRequest;
import com.east.demo.service.commonrecord.order.interfac.Generate;
import org.springframework.stereotype.Component;

/**
 * 外部渠道生成账单信息
 *
 * @author: east
 * @date: 2023/11/24
 */
@Component
public class OuterGenerateOrder implements Generate<OuterOrderRequest, OuterNeededSavedInfo> {
    /**
     * 生成下单信息
     *
     * @param neededInfo 必要信息
     * @return result R
     */
    @Override
    public OuterNeededSavedInfo generate(OuterOrderRequest neededInfo) {
        OuterNeededSavedInfo outerNeededSavedInfo = new OuterNeededSavedInfo();
        System.out.println("外部渠道下单信息开始生成");

        return outerNeededSavedInfo;
    }
}
