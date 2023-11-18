package com.east.demo.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Supplier;

/**
 * 基本异常类
 *
 * @author: east
 * @date: 2023/11/11
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {
    protected ErrorEnum errorEnum;

    public BaseException(ErrorEnum errorEnum) {
        super(errorEnum.getErrorMsg());
        this.errorEnum = errorEnum;
    }

    /**
     * 这里用Supplier而不是直接String的原因有两个：
     * 1. 延迟执行：如果生成消息的代价较高（例如涉及计算或IO操作），使用 Supplier 可以延迟执行这些操作，直到真正需要消息时。这可以提高性能，特别是在消息可能不被使用的情况下（例如，基于某些条件日志不打印）。
     * 2. 条件执行：在某些情况下，消息的创建本身可能依赖于特定的条件。使用 Supplier，您可以在函数内部根据需要决定是否调用 get()。
     *
     * @param errorEnum 错误码
     * @param message   message的lambda表达式
     */
    public BaseException(ErrorEnum errorEnum, Supplier<String> message) {
        super(message.get());
        this.errorEnum = errorEnum;
    }

    public BaseException(ErrorEnum errorEnum, Supplier<String> message, Throwable cause) {
        super(errorEnum.getErrorMsg(), cause);
        this.errorEnum = errorEnum;
    }

}
