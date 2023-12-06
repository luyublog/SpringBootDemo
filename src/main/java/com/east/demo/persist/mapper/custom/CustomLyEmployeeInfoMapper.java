package com.east.demo.persist.mapper.custom;

import com.east.demo.model.dto.test.LyEmployeeInfoDTO;
import com.east.demo.persist.mapper.base.LyEmployeeInfoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: east
 * @date: 2023/11/1
 */

@Mapper
public interface CustomLyEmployeeInfoMapper extends LyEmployeeInfoMapper {
    /**
     * 通过name查询
     */
    LyEmployeeInfoDTO getByName(String name);

    /**
     * 通过map传参
     */
    LyEmployeeInfoDTO getByMap(Map<String, Object> name);

    /**
     * 多参数绑定情况
     */
    LyEmployeeInfoDTO getByFullName(String firstName, String lastName, String jobId);

    /**
     * 多参数绑定情况
     */
    LyEmployeeInfoDTO getByFullName2(@Param("firstName") String arg0, @Param("lastName") String arg1, @Param("jobId") String arg2);

    /**
     * 获取包含job信息在内的信息
     */
    LyEmployeeInfoDTO getInfoWithJobByFirstName(@Param("firstName") String firstName);

    /**
     * 用了一些标签的sql
     */
    LyEmployeeInfoDTO getBySpecific(@Param("firstName") String firstName,
                                    @Param("lastName") String lastName,
                                    @Param("jobId") String jobId,
                                    @Param("jobIdList") List<String> jobIdList);

    int updateSexByEmployeeId(@Param("employeeId") Long employeeId, @Param("sexId") String sexId);
}
