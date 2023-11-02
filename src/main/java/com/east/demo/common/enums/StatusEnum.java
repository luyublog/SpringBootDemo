package com.east.demo.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 状态枚举测试类
 *
 * @author: east
 * @date: 2023/11/2
 */

@JsonFormat
public enum StatusEnum {
    INIT("N"),
    VALID("A"),
    DELETED("D"),
    ;
    private final String status;

    StatusEnum(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
