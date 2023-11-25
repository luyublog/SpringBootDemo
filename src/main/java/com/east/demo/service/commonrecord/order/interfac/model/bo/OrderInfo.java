package com.east.demo.service.commonrecord.order.interfac.model.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInfo {
    protected BigDecimal amt;
    protected String serial;
    protected String rcvNo;
}