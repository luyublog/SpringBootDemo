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
     * 自定义的概略信息,可以定义为内部类，也可以在外部继承CommonSummary后使用
     * 放这里两个目的：
     * 1. 方便使用，不建新类了
     * 2. 测试内部类在resultMap中使用时顺序问题  todo 因为没有设置默认构造函数造成mybatis处理返回结果时无法找到map的对应关系。奇怪？为什么之前生成的是全参构造，而这里是无参？
     *
     */
    @Data
    public static class MyCommonSummary extends CommonSummary {
        private String total;
    }
}
