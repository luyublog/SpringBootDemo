package com.east.demo.common.exception;

/**
 * 错误代码
 *
 * @author: east
 * @date: 2023/10/8
 */
//@Getter
public enum ErrorEnum implements ErrorInterface {
    /**
     * 失败
     */
    FAIL("ERROR000", "失败"),
    /**
     * 成功
     */
    SUCCESS("SUCCESS", "成功"),
    /**
     * header填放json响应数据特殊错误码
     */
    HEADER_RESPONSE("ERROR001", "特殊错误"),
    ;
    private final String code;
    private final String msg;

    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }

    @Override
    public String getErrorMsg(Object... msg) {
        return ErrorInterface.super.getErrorMsg(msg);
    }
}
