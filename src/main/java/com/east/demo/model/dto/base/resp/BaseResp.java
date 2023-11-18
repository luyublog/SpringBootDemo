package com.east.demo.model.dto.base.resp;

import com.east.demo.common.exception.ErrorEnum;
import lombok.Data;

/**
 * Description:
 * Created by east on 2023/8/6 18:09.
 */
@Data
//@NoArgsConstructor
public class BaseResp<T> {
    private String respCode;
    private String respMsg;
    private T data;

    public BaseResp(ErrorEnum errorEnum) {
        this.respCode = errorEnum.getErrorCode();
        this.respMsg = errorEnum.getErrorMsg();
    }

    public BaseResp(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    public BaseResp(ErrorEnum errorEnum, T data) {
        this.respCode = errorEnum.getErrorCode();
        this.respMsg = errorEnum.getErrorMsg();
        this.data = data;
    }

    public static <T> BaseResp<T> ok(T data) {
        return new BaseResp<T>(ErrorEnum.SUCCESS, data);
    }
}

