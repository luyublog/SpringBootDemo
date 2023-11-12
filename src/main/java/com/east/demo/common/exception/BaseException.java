package com.east.demo.common.exception;

import lombok.Data;

import java.util.function.Supplier;

/**
 * 基本异常类
 *
 * @author: east
 * @date: 2023/11/11
 */

@Data
public class BaseException extends RuntimeException {
    protected ErrorEnum errorEnum;

    public BaseException(ErrorEnum errorEnum) {
        super(errorEnum.getErrorMsg());
        this.errorEnum = errorEnum;
    }

    public BaseException(ErrorEnum errorEnum, Supplier<String> message) {
        super(message.get());
        this.errorEnum = errorEnum;
    }

    public BaseException(ErrorEnum errorEnum, Throwable cause) {
        super(errorEnum.getErrorMsg(), cause);
        this.errorEnum = errorEnum;
    }

}
