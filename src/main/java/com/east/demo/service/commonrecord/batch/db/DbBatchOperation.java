package com.east.demo.service.commonrecord.batch.db;

import com.east.demo.persist.entity.base.LyEmployeeInfo;
import com.east.demo.persist.mapper.custom.CustomLyEmployeeInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
@Slf4j
public class DbBatchOperation {
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    CustomLyEmployeeInfoMapper customLyEmployeeInfoMapper;

    public <T> int insertBatch(List<T> infoList, BiFunction<SqlSession, T, Integer> function) {
        int batchSize = 1000;

//        SqlSession sqlSession = null;
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
            int i = 0;
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

    /**
     * todo 测试在大数量下时，openSession为Batch模式下如果要获取每笔结果该怎么优化
     *
     * @param lyEmployeeInfoList
     */
    public void batchInsert(List<LyEmployeeInfo> lyEmployeeInfoList) {
        for (LyEmployeeInfo lyEmployeeInfo : lyEmployeeInfoList) {
            try {
                customLyEmployeeInfoMapper.insert(lyEmployeeInfo);
            } catch (DuplicateKeyException duplicateKeyException) {
                log.info("重复数据,id:{}", lyEmployeeInfo.getEmployeeId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * todo 测试大数据情况下，for循环更新，批量case when，update by select三种哪种更新效率更高
     *
     * @param lyEmployeeInfoList
     */
    public void batchUpdate(List<LyEmployeeInfo> lyEmployeeInfoList) {
        for (LyEmployeeInfo lyEmployeeInfo : lyEmployeeInfoList) {
//            customLyEmployeeInfoMapper.updateSexById(lyEmployeeInfo);
        }
    }
}
