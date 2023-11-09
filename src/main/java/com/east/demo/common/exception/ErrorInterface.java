package com.east.demo.common.exception;

import java.text.MessageFormat;

/**
 * 错误码接口, 主要用于扩展错误类型（常用于公共工程中）
 *
 * <a href="https://demonlee.tech/archives/java8-interface-default-method">关于接口的默认实现default</a>
 *
 * @author: east
 * @date: 2023/11/9
 */
public interface ErrorInterface {
    String getErrorCode();

    String getErrorMsg();

    default String getErrorMsg(Object... params) {
        return MessageFormat.format(this.getErrorMsg(), params);
    }
}
