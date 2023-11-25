package com.east.demo.service.commonrecord.order.imp.save;

import com.east.demo.service.commonrecord.order.imp.model.bo.InnerNeededSavedInfo;
import com.east.demo.service.commonrecord.order.interfac.Save;
import org.springframework.stereotype.Component;

/**
 * 内部渠道保存账单
 *
 * @author: east
 * @date: 2023/11/24
 */
@Component
public class InnerSaveOrder implements Save<InnerNeededSavedInfo> {

    /**
     * 保存下单数据
     *
     * @param savedInfo 相关信息:账单表，统计表
     */
    @Override
    public void save(InnerNeededSavedInfo savedInfo) {
        System.out.println("内部渠道下单信息开始落表");
    }
}
