package com.east.demo.dto.resp;

import com.east.demo.common.enums.ErrorEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

/**
 * Description:
 * Created by east on 2023/8/6 18:09.
 */
@Data
@NoArgsConstructor
public class BaseResp {
    private String respCode;
    private String respMsg;
    private T data;

    public BaseResp(ErrorEnum errorEnum) {
        this.respCode = errorEnum.getCode();
        this.respMsg = errorEnum.getMsg();
    }

    public BaseResp(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }
}

