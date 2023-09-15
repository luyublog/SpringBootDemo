package com.east.demo.dto.download;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @description:
 * @author: east
 * @date: 2023/9/13
 */
@Data
public class DownloadHeader {
    @ExcelProperty(index = 0)
    private String id;

    @ExcelProperty(index = 1)
    private String name;

    @ExcelProperty(index = 2)
    private String fatherId;
}
