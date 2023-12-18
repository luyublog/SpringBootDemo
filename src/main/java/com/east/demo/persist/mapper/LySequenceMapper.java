package com.east.demo.persist.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 获取序列mapper
 *
 * @author: east
 * @date: 2023/11/4
 */

@Mapper
public interface LySequenceMapper {
    /**
     * 获取一个队列元素
     *
     * @return long
     */
    Long selectSequence();

    /**
     * 获取指定队列名称的队列值
     */
    Long selectSequenceByName(@Param("sequenceName") String sequenceName);

    /**
     * todo 测试下这里用toChar对队列进行转换后对速度的影响
     *
     * @param sequenceName 队列名称
     * @param size         数量
     * @return queue
     */
    LinkedBlockingQueue<Long> selectSequenceByNameAndSize(@Param("sequenceName") String sequenceName,
                                                          @Param("size") Integer size);
}
