package com.east.demo.common.handler;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OraclePreparedStatement;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Char类型处理
 *
 * @author: east
 * @date: 2023/11/9
 */
@Slf4j
@MappedJdbcTypes(JdbcType.CHAR)
public class CharTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        OraclePreparedStatement preparedStatement = ps.unwrap(OraclePreparedStatement.class);
        preparedStatement.setFixedCHAR(i, parameter);
        if (log.isDebugEnabled()) {
            log.debug("==> Fixed Parameters:{}", parameter);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }
}
