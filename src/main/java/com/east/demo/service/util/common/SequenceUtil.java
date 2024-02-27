package com.east.demo.service.util.common;

import cn.hutool.core.util.StrUtil;
import com.east.demo.persist.mapper.LySequenceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 序列相关公共方法
 *
 * @author: east
 * @date: 2023/12/18
 */

@Service
@Slf4j
public class SequenceUtil {
    private final LySequenceMapper lySequenceMapper;

    @Autowired
    public SequenceUtil(LySequenceMapper lySequenceMapper) {
        this.lySequenceMapper = lySequenceMapper;
    }

    /**
     * 调用存储过程添加序列， todo 支持并发调用
     *
     * @param sequenceName 序列名称
     */
    public void addSequence(String sequenceName) {
        Map<String, Object> msg = new HashMap<>();
        lySequenceMapper.addSequence(sequenceName, msg);
        log.info((String) msg.get("msg"));
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

    /**
     * 批量获取序列
     *
     * @param name 序列名称
     * @param size 个数
     * @return 序列
     */
    public LinkedBlockingQueue<Long> getSequences(String name, Integer size) {
        long startTime = System.currentTimeMillis();
        LinkedBlockingQueue<Long> sequences = lySequenceMapper.selectSequenceByNameAndSize(name, size);
        log.info("队列名称:{}, 获取{}个序列, 耗费时间：{}ms", name, size, System.currentTimeMillis() - startTime);
        return sequences;
    }

    /**
     * 获取自定义32进制规则转账码
     *
     * @param name 队列名称
     * @param size 个数
     * @return result
     */
    public ArrayList<String> getTransferCode(String name, Integer size) {
        // 获取序列
        LinkedBlockingQueue<Long> sequences = getSequences(name, size);
        ArrayList<String> result = new ArrayList<>(sequences.size());
        // 转换为32进制转账码
        for (Long sequence : sequences) {
            long l = CommonUtil.encryptLong(sequence);
            String transferCode = CommonUtil.base32Encode(l);
            result.add(transferCode);
        }
        return result;
    }


//    public static void main(String[] args) {
//        SequenceUtil sequenceUtil = new SequenceUtil(new LySequenceMapper() {
//            /**
//             * 获取一个队列元素
//             *
//             * @return long
//             */
//            @Override
//            public Long selectSequence() {
//                return null;
//            }
//
//            /**
//             * 获取指定队列名称的队列值
//             *
//             * @param sequenceName
//             */
//            @Override
//            public Long selectSequenceByName(String sequenceName) {
//                return null;
//            }
//
//            /**
//             * todo 测试下这里用toChar对队列进行转换后对速度的影响
//             *
//             * @param sequenceName 队列名称
//             * @param size         数量
//             * @return queue
//             */
//            @Override
//            public LinkedBlockingQueue<Long> selectSequenceByNameAndSize(String sequenceName, Integer size) {
//                return null;
//            }
//        });
//        // 不打乱顺序会被枚举出来
//        System.out.println(sequenceUtil.base32Encode(0L));
//        System.out.println(sequenceUtil.base32Encode(1L));
//        System.out.println(sequenceUtil.base32Encode(2L));
//        System.out.println(sequenceUtil.base32Encode(3L));
//
//        // 打乱实现伪随机
//        System.out.println(sequenceUtil.base32Encode(sequenceUtil.encryptLong(0L)));
//        System.out.println(sequenceUtil.base32Encode(sequenceUtil.encryptLong(1L)));
//        System.out.println(sequenceUtil.base32Encode(sequenceUtil.encryptLong(2L)));
//        System.out.println(sequenceUtil.base32Encode(sequenceUtil.encryptLong(3L)));
//
//    }
}
