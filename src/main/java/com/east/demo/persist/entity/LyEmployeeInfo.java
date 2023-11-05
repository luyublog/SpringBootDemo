package com.east.demo.persist.entity;

import com.east.demo.common.enums.SexEnum;
import lombok.*;

import java.io.Serializable;

/**
 * @author: east
 * @date: 2023/11/1
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LyEmployeeInfo implements Serializable {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private SexEnum sex;
    private Double salary;
    private String jobId;
    private LyJobInfo jobInfo;
}
