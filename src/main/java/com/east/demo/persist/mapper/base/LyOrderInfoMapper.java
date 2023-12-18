package com.east.demo.persist.mapper.base;

import com.east.demo.persist.entity.base.LyOrderInfo;

public interface LyOrderInfoMapper {
    int deleteByPrimaryKey(String orderSerial);

    int insert(LyOrderInfo record);

    int insertSelective(LyOrderInfo record);

    LyOrderInfo selectByPrimaryKey(String orderSerial);

    int updateByPrimaryKeySelective(LyOrderInfo record);

    int updateByPrimaryKey(LyOrderInfo record);
}