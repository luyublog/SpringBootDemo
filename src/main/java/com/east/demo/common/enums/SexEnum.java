package com.east.demo.common.enums;

import lombok.Getter;

/**
 * 性别枚举
 *
 * @author: east
 * @date: 2023/11/5
 */

@Getter
public enum SexEnum {
    /**
     * 未知性别
     */
    UNKNOWN("2", "未知"),

    /**
     * 男
     */
    MAN("1", "男"),

    /**
     * 女
     */
    WOMAN("0", "女");


    private final String value;
    private final String description;

    SexEnum(String value, String name) {
        this.value = value;
        this.description = name;
    }

    public String getDescription() {
        return description;
    }

    public static SexEnum getByValue(String value) {
        for (SexEnum item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
