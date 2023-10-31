package com.east.demo.service.commonrecord.batch.db;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

/**
 * 数据库批量操作方法
 *
 * @author: east
 * @date: 2023/10/30
 */

@Service
public class DbBatchOperation {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public <T> int insertBatch(List<T> infoList, BiFunction<SqlSession, T, Integer> function) {
        int batchSize = 1000;

        SqlSession sqlSession = null;
        try {
            int i = 0;
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            for (T info : infoList) {
                i++;
                Integer apply = function.apply(sqlSession, info);
                if (i % batchSize == 0) {
                    // 提交
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
            if (i % batchSize != 0) {
                sqlSession.commit();
                sqlSession.clearCache();
            }
            return i;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
