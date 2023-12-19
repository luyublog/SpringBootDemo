package com.east.demo.persist.mapper.custom;

import com.east.demo.persist.entity.base.LyOrderInfo;
import com.east.demo.persist.mapper.base.LyOrderInfoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: east
 * @date: 2023/11/1
 */

@Mapper
public interface CustomLyOrderInfoMapper extends LyOrderInfoMapper {
    /**
     * 获取全量数据
     *
     * @return all
     */
    List<LyOrderInfo> getAll(@Param("size") Integer size);

    /**
     * 单条更新数据
     *
     * @param lyOrderInfo orderInfo
     * @return result
     */
    int updateRvcNoBySerial(@Param("lyOrderInfo") LyOrderInfo lyOrderInfo);

    /**
     * 批量更新数据.注意1k条限制
     *
     * @param lyOrderInfoList lyOrderInfoList
     * @return result
     */
    int updateRvcNoBySerialList(@Param("lyOrderInfoList") List<LyOrderInfo> lyOrderInfoList);

    /**
     * 批量更新数据通过连表更新 注意1k条限制
     *
     * @return result
     */
    int updateRvcNoByOtherTable(@Param("lyOrderInfoList") List<LyOrderInfo> lyOrderInfoList);
}
