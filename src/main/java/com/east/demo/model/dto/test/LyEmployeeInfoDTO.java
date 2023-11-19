package com.east.demo.model.dto.test;

import com.east.demo.common.enums.SexEnum;
import com.east.demo.persist.entity.LyJobInfo;
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
public class LyEmployeeInfoDTO implements Serializable {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private SexEnum sex;
    private Double salary;
    private String jobId;
    private LyJobInfo jobInfo;

//    @Override
//    public String toString() {
//        return "LyEmployeeInfo{" +
//                "employeeId=" + employeeId +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", sex=" + sex.getValue() +
//                ", salary=" + salary +
//                ", jobId='" + jobId + '\'' +
//                ", jobInfo=" + jobInfo +
//                '}';
//    }
}
