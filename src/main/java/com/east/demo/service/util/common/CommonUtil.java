package com.east.demo.service.util.common;

import cn.hutool.core.util.StrUtil;

/**
 * 通用工具类
 *
 * @author: east
 * @date: 2024/2/27 21:25
 */

public class CommonUtil {

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



    /**
     * 将序列（序列最大值不要超过32^5否则会生成重复）打乱为8位，从而防止自定义的32位进制字符被枚举出来，
     * 打乱顺序时第一位不能变，因为要保证数字在32^5=33,554,432范围内，从而避免重复
     *
     * @param num 序列值
     * @return 打乱顺序后的序列
     */
    public static long encryptLong(long num) {
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
    public static String base32Encode(long num) {
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
}
