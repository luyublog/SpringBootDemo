package com.east.demo.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 响应头填放json数据特殊响应异常
 *
 * @author: east
 * @date: 2023/10/8
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class HeaderRespException extends RuntimeException {
    private final String code;
    private final String msg;

    public HeaderRespException(String message, Throwable cause, String code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }
}
