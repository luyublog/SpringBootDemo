package com.east.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Created by east on 2023/8/6 18:09.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResp {
    private String respCode;
    private String respMsg;
}

