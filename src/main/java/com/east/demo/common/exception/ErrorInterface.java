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
    /**
     * 错误码
     *
     * @return 错误码
     */
    String getErrorCode();

    /**
     * 错误信息
     * @return 错误信息
     */
    String getErrorMsg();

    /**
     * 错误信息
     * @param params 自定义部分
     * @return 错误信息包含自定义部分
     */
    default String getErrorMsg(Object... params) {
        return MessageFormat.format(this.getErrorMsg(), params);
    }
}
