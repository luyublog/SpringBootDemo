package com.east.demo.pojo.dto.base.req;

import lombok.Data;

/**
 * 基本请求报文
 *
 * @author: east
 * @date: 2023/11/2
 */

@Data
public class BaseReq {
    private String userId;
    private String userName;
}
