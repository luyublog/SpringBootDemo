package com.east.demo.common.enums;

import lombok.Getter;

/**
 * 错误代码
 *
 * @author: east
 * @date: 2023/10/8
 */
@Getter
public enum ErrorEnum {
    FAIL("error", "未知异常"),
    SUCCESS("success", "成功"),
    HEADER_RESPONSE("error", "header填放json响应数据特殊错误码"),
    ;
    private final String code;
    private final String msg;

    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
