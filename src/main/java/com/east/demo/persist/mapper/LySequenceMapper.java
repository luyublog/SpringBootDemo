package com.east.demo.persist.mapper;

import org.mapstruct.Mapper;

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
}
