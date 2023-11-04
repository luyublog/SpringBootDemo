package com.east.demo.persist.mapper;

import com.east.demo.persist.entity.LyUserInfo;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.Map;

/**
 * @author: east
 * @date: 2023/11/1
 */

@Mapper
public interface LyUserInfoMapper {
    /**
     * 通过name查询
     */
    LyUserInfo getByName(String name);

    /**
     * 通过map传参
     */
    LyUserInfo getByMap(Map<String, Object> name);

    /**
     * 多参数绑定情况
     */
    LyUserInfo getByFullName(String firstName, String lastName, String jobId);

    /**
     * 多参数绑定情况
     */
    LyUserInfo getByFullName2(@Param("firstName") String arg0, @Param("lastName") String arg1, @Param("jobId") String arg2);
}
