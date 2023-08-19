package com.east.demo.persist.mapper;

import com.east.demo.dto.LyUserOrganInfo;
import com.east.demo.persist.entity.LyUserOrgan;
import org.apache.ibatis.annotations.MapKey;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface LyUserOrganMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LyUserOrgan record);

    int insertSelective(LyUserOrgan record);

    LyUserOrgan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LyUserOrgan record);

    int updateByPrimaryKey(LyUserOrgan record);

    @MapKey(value = "id")
    Map<Integer, LyUserOrganInfo> selectAll();
}