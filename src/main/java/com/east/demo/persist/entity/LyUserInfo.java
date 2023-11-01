package com.east.demo.persist.entity;

import lombok.*;

/**
 * @author: east
 * @date: 2023/11/1
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LyUserInfo {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    //    private Integer age;
    private Double salary;
//    private Integer sex;
}
