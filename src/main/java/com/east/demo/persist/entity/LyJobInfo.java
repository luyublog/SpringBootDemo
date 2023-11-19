package com.east.demo.persist.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Job信息
 *
 * @author: east
 * @date: 2023/11/5
 */

// 这里没有final数据，所以@Data包含的@RequiredArgsConstructor生成了无参构造
@Data
@ToString
public class LyJobInfo implements Serializable {
    private String jobId;
    private String jobTitle;
    // 这里用Short方便模拟金额溢出情况
    private Short minSalary;
    private Integer maxSalary;

    @Data
    public static class InnerTestClass {
        private String innerString1;
        private String innerString2;
    }
}
