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
public class BasePageInfoResp<S extends CommonSummary, T> {
    /**
     * 这里用extends CommonSummary限定原因主要是：
     * 保证响应的概略信息有统一的格式
     */
    protected S summary;
    protected List<T> detail;

    /**
     * 自定义的概略信息
     */
    @Data
    public static class TestCommonSummary extends CommonSummary {
        private String testString;
    }
}
