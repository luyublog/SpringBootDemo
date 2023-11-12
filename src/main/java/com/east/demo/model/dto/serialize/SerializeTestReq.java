package com.east.demo.model.dto.serialize;

import com.east.demo.common.enums.StatusEnum;
import com.east.demo.model.dto.base.req.BaseReq;
import lombok.Data;
import lombok.ToString;

/**
 * 含枚举类的反序列化测试请求体
 *
 * @author: east
 * @date: 2023/11/2
 */

@Data
@ToString
public class SerializeTestReq extends BaseReq {
    private StatusEnum status;
}
