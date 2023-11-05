package com.east.demo.persist.mapper;

import org.apache.ibatis.annotations.Param;
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

    /**
     * 获取指定队列名称的队列值
     */
    Long selectSequenceByName(@Param("sequenceName") String sequenceName);
}
