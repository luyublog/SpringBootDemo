package com.east.demo.service.util.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
@Slf4j
class SequenceUtilTest {
    @Autowired
    SequenceUtil sequenceUtil;

    @Test
    void getTransferCode() {
        ArrayList<String> codes = sequenceUtil.getTransferCode("LY_SEQ", 3);
        for (String code : codes) {
            log.info(code + ", ");
        }
    }

    @Test
    void addSequence() {
        String sequenceName = "test";
        // 开5个线程模拟并发
        CompletableFuture<Void> run1 = CompletableFuture.runAsync(() -> {
            sequenceUtil.addSequence(sequenceName);
        });
        CompletableFuture<Void> run2 = CompletableFuture.runAsync(() -> {
            sequenceUtil.addSequence(sequenceName);
        });
        CompletableFuture<Void> run3 = CompletableFuture.runAsync(() -> {
            sequenceUtil.addSequence(sequenceName);
        });
        CompletableFuture<Void> run4 = CompletableFuture.runAsync(() -> {
            sequenceUtil.addSequence(sequenceName);
        });
        CompletableFuture<Void> run5 = CompletableFuture.runAsync(() -> {
            sequenceUtil.addSequence(sequenceName);
        });

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(run1, run2, run3, run4, run5);
        try {
            voidCompletableFuture.get();
        } catch (Exception e) {
            log.error("add sequence error: ", e);
        }

    }
}