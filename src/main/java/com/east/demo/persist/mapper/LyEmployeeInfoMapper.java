package com.east.demo.persist.mapper;

import com.east.demo.persist.entity.LyEmployeeInfo;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: east
 * @date: 2023/11/1
 */

@Mapper
public interface LyEmployeeInfoMapper {
    /**
     * 通过name查询
     */
    LyEmployeeInfo getByName(String name);

    /**
     * 通过map传参
     */
    LyEmployeeInfo getByMap(Map<String, Object> name);

    /**
     * 多参数绑定情况
     */
    LyEmployeeInfo getByFullName(String firstName, String lastName, String jobId);

    /**
     * 多参数绑定情况
     */
    LyEmployeeInfo getByFullName2(@Param("firstName") String arg0, @Param("lastName") String arg1, @Param("jobId") String arg2);

    /**
     * 获取包含job信息在内的信息
     */
    LyEmployeeInfo getInfoWithJobByFirstName(@Param("firstName") String firstName);

    /**
     * 用了一些标签的sql
     */
    LyEmployeeInfo getBySpecific(@Param("firstName") String firstName,
                                 @Param("lastName") String lastName,
                                 @Param("jobId") String jobId,
                                 @Param("jobIdList") List<String> jobIdList);

    int updateSexByEmployeeIdInt(@Param("employeeId") Long employeeId);
}
