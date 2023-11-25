package com.east.demo.service.commonrecord.order.imp.generate;

import com.east.demo.service.commonrecord.order.imp.model.bo.InnerNeededSavedInfo;
import com.east.demo.service.commonrecord.order.imp.model.req.InnerOrderRequest;
import com.east.demo.service.commonrecord.order.interfac.Generate;
import org.springframework.stereotype.Component;

/**
 * 内部渠道生成账单相关信息
 *
 * @author: east
 * @date: 2023/11/24
 */
@Component
public class InnerGenerateOrder implements Generate<InnerOrderRequest, InnerNeededSavedInfo> {


    @Override
    public InnerNeededSavedInfo generate(InnerOrderRequest neededInfo) {
        InnerNeededSavedInfo innerNeededSavedInfo = new InnerNeededSavedInfo();
        System.out.println("内部渠道下单信息开始生成");

        return innerNeededSavedInfo;
    }
}
