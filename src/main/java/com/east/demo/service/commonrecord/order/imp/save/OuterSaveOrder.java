package com.east.demo.service.commonrecord.order.imp.save;

import com.east.demo.service.commonrecord.order.imp.model.bo.OuterNeededSavedInfo;
import com.east.demo.service.commonrecord.order.interfac.Save;
import org.springframework.stereotype.Component;

/**
 * 外部渠道下单信息落表
 *
 * @author: east
 * @date: 2023/11/25
 */

@Component
public class OuterSaveOrder implements Save<OuterNeededSavedInfo> {
    /**
     * 保存下单数据
     *
     * @param savedInfo 相关信息:账单表，统计表
     */
    @Override
    public void save(OuterNeededSavedInfo savedInfo) {
        System.out.println("外部渠道下单信息开始落表");
    }
}
