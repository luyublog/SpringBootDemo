package com.east.demo.persist.entity;

import lombok.Data;
import lombok.ToString;

/**
 * Job信息
 *
 * @author: east
 * @date: 2023/11/5
 */

@Data
@ToString
public class LyJobInfo {
    private String jobId;
    private String jobTitle;
    private Integer minSalary;
    private Integer maxSalary;
}
