package com.east.demo.persist.mapper;

import com.east.demo.persist.entity.LyJobInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: east
 * @date: 2023/11/1
 */

@Mapper
public interface LyJobInfoMapper {
    /**
     * 通过jobId查询
     */
    LyJobInfo getByJobId(@Param("jobId") String jobId);
}
