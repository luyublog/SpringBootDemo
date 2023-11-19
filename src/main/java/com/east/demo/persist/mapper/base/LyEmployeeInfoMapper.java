package com.east.demo.persist.mapper.base;

import com.east.demo.persist.entity.base.LyEmployeeInfo;
import org.mapstruct.Mapper;

@Mapper
public interface LyEmployeeInfoMapper {
    int deleteByPrimaryKey(Integer employeeId);

    int insert(LyEmployeeInfo record);

    int insertSelective(LyEmployeeInfo record);

    LyEmployeeInfo selectByPrimaryKey(Integer employeeId);

    int updateByPrimaryKeySelective(LyEmployeeInfo record);

    int updateByPrimaryKey(LyEmployeeInfo record);
}