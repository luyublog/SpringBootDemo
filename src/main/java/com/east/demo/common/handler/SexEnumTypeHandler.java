package com.east.demo.common.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.east.demo.common.enums.SexEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 性别枚举转换类
 *
 * @author: east
 * @date: 2023/11/5
 */

@Slf4j
@MappedJdbcTypes({JdbcType.CHAR, JdbcType.VARCHAR})
public class SexEnumTypeHandler extends BaseTypeHandler<SexEnum> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
        log.info("性别枚举处理器设置值{}", parameter.getValue());
    }


    @Override
    public SexEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        String value = StrUtil.trim(Convert.toStr(object, "2"));
        return SexEnum.getByValue(value);
    }


    @Override
    public SexEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object object = rs.getObject(columnIndex);
        String value = StrUtil.trim(Convert.toStr(object, "2"));
        return SexEnum.getByValue(value);
    }


    @Override
    public SexEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object object = cs.getObject(columnIndex);
        String value = StrUtil.trim(Convert.toStr(object, "2"));
        return SexEnum.getByValue(value);
    }
}
