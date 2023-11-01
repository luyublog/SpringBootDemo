package com.east.demo.persist.mapper;

import com.east.demo.persist.entity.LyUserInfo;
import org.mapstruct.Mapper;

/**
 * @author: east
 * @date: 2023/11/1
 */

@Mapper
public interface LyUserInfoMapper {
    /**
     * 通过name查询
     *
     * @param name
     * @return
     */
    LyUserInfo getByName(String name);
}
