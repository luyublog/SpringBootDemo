package com.east.demo.model.dto.middle.mq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * mq通知消息结构体
 *
 * @author: east
 * @date: 2023/10/27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageStruct implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
}
