package com.east.demo.service.util.common;

import cn.hutool.core.util.StrUtil;
import com.east.demo.persist.mapper.LySequenceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 公共方法
 *
 * @author: east
 * @date: 2023/12/18
 */

@Service
@Slf4j
public class CommonUtil {
    private final LySequenceMapper lySequenceMapper;

    @Autowired
    public CommonUtil(LySequenceMapper lySequenceMapper) {
        this.lySequenceMapper = lySequenceMapper;
    }

    /**
     * 4位uniqueNum加12位序列号组成16位流水号
     *
     * @param uniqueNum 特殊编号
     * @param num       序列
     * @return 序列
     */
    public String tranSequenceToFormatString(String uniqueNum, Long num) {
        return uniqueNum + StrUtil.fillBefore(num.toString(), '0', 12);
    }

    public LinkedBlockingQueue<Long> getSequences(String name, Integer size) {
        long startTime = System.currentTimeMillis();
        LinkedBlockingQueue<Long> sequences = lySequenceMapper.selectSequenceByNameAndSize(name, size);
        log.info("队列名称:{}, 获取{}个序列, 耗费时间：{}ms", name, size, System.currentTimeMillis() - startTime);
        return sequences;
    }
}
