package com.east.demo.service.util.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@Slf4j
class CommonUtilTest {
    @Autowired
    CommonUtil commonUtil;

    @Test
    void getTransferCode() {
        ArrayList<String> codes = commonUtil.getTransferCode("LY_SEQ", 3);
        for (String code : codes) {
            log.info(code + ", ");
        }
    }
}