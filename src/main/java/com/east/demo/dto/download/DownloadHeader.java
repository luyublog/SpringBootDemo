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
    @ExcelProperty({"顶格", "顶格", "两格"})
    private Integer id;

    @ExcelProperty({"顶格", "四联", "四联"})
    private String name;

    @ExcelProperty({"顶格"})
    private Integer fatherId;
}
