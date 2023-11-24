package com.east.demo.service.commonrecord.order.imp.model.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 账单对应信息
 *
 * @author: east
 * @date: 2023/11/23
 */
@Data
public class OrderInfo {
    protected BigDecimal amt;
    protected String serial;
    protected String rcvNo;
}
