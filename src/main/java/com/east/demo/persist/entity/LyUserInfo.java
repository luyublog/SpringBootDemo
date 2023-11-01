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
    private Long id;
    private String name;
    private Integer age;
    private Double salary;
    private Integer sex;
}
