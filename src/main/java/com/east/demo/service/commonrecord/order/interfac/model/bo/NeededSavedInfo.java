package com.east.demo.service.commonrecord.order.interfac.model.bo;


import com.east.demo.persist.entity.base.LyEmployeeInfo;
import lombok.Data;

/**
 * 下单落表的最基本信息
 * 账单信息
 * 其他信息自行继承本类实现
 *
 * @author: east
 * @date: 2023/11/25
 */

@Data
public class NeededSavedInfo {
    protected LyEmployeeInfo orderInfo;
}
