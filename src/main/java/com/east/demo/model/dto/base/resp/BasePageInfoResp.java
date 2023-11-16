package com.east.demo.model.dto.base.resp;

import lombok.Data;

import java.util.List;

/**
 * 分页响应信息
 *
 * @author: east
 * @date: 2023/11/16
 */
@Data
public class BasePageInfoResp<T> {
    /**
     * 这里用通用类而不用模板的原因主要是：
     * 保证响应的概略信息有统一的格式
     */
    protected CommonSummary summary;
    protected List<T> detail;

}
