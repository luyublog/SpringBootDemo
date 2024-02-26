package com.east.demo.service.util.common;

import cn.hutool.core.util.StrUtil;
import com.east.demo.persist.mapper.LySequenceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 将序列字符重新排列的规则
     */
    private static final Integer[] SEQ_ORDER_LIST = {1, 8, 7, 6, 5, 4, 3, 2};
    /**
     * 32进制符号来源
     */
    private static final String CHARS_SOURCE = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    /**
     * 打乱后的32进制符号来源
     */
    private static final String CHARS_SOURCE_RANDOM = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

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
            long l = encryptLong(sequence);
            String transferCode = base32Encode(l);
            result.add(transferCode);
        }
        return result;
    }

    /**
     * 将序列（序列最大值不要超过32^5否则会生成重复）打乱为8位，从而防止自定义的32位进制字符被枚举出来，
     * 打乱顺序时第一位不能变，因为要保证数字在32^5=33,554,432范围内，从而避免重复
     *
     * @param num 序列值
     * @return 打乱顺序后的序列
     */
    private long encryptLong(long num) {
        // 将序列格式化为至少8位的字符数组（不足8位用0补齐）
        char[] originNum = String.format("%08d", num).toCharArray();
        StringBuilder str = new StringBuilder();
        // 将序列字符重新排列的规则
        for (Integer order : SEQ_ORDER_LIST) {
            str.append(originNum[order - 1]);
        }
        return Long.parseLong(str.toString());
    }

    /**
     * 对数字进行自定义32进制加密
     *
     * @param num 需要转换的数字
     * @return 结果为5位字符
     */
    private String base32Encode(long num) {
        StringBuilder base32Str = new StringBuilder();
        long quotient = num;
        // 计算自定义字符32进制结果
        while (quotient > 0) {
            // 获取余数
            long remainder = quotient % 32;
            base32Str.append(CHARS_SOURCE_RANDOM.charAt(Math.toIntExact(remainder)));
            // 将商代入下次计算
            quotient /= 32;
        }
        String result = base32Str.reverse().toString();
        // 返回5位字符，多了截取前五位，少了用第一个字符替代
        return StrUtil.padPre(result, 5, CHARS_SOURCE_RANDOM.charAt(0));
    }


    public static void main(String[] args) {
        CommonUtil commonUtil = new CommonUtil(new LySequenceMapper() {
            /**
             * 获取一个队列元素
             *
             * @return long
             */
            @Override
            public Long selectSequence() {
                return null;
            }

            /**
             * 获取指定队列名称的队列值
             *
             * @param sequenceName
             */
            @Override
            public Long selectSequenceByName(String sequenceName) {
                return null;
            }

            /**
             * todo 测试下这里用toChar对队列进行转换后对速度的影响
             *
             * @param sequenceName 队列名称
             * @param size         数量
             * @return queue
             */
            @Override
            public LinkedBlockingQueue<Long> selectSequenceByNameAndSize(String sequenceName, Integer size) {
                return null;
            }
        });
        // 不打乱顺序会被枚举出来
        System.out.println(commonUtil.base32Encode(0L));
        System.out.println(commonUtil.base32Encode(1L));
        System.out.println(commonUtil.base32Encode(2L));
        System.out.println(commonUtil.base32Encode(3L));

        // 打乱实现伪随机
        System.out.println(commonUtil.base32Encode(commonUtil.encryptLong(0L)));
        System.out.println(commonUtil.base32Encode(commonUtil.encryptLong(1L)));
        System.out.println(commonUtil.base32Encode(commonUtil.encryptLong(2L)));
        System.out.println(commonUtil.base32Encode(commonUtil.encryptLong(3L)));

    }
}
