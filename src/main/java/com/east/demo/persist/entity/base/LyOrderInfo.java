package com.east.demo.persist.entity.base;

import java.math.BigDecimal;

public class LyOrderInfo {
    private String orderSerial;

    private BigDecimal orderAmt;

    private String orderRcvNo;

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial == null ? null : orderSerial.trim();
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getOrderRcvNo() {
        return orderRcvNo;
    }

    public void setOrderRcvNo(String orderRcvNo) {
        this.orderRcvNo = orderRcvNo == null ? null : orderRcvNo.trim();
    }
}