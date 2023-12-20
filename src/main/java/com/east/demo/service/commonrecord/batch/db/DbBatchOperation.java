package com.east.demo.service.commonrecord.batch.db;

import cn.hutool.core.collection.CollUtil;
import com.east.demo.persist.entity.base.LyOrderInfo;
import com.east.demo.persist.mapper.custom.CustomLyOrderInfoMapper;
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
    private final SqlSessionFactory sqlSessionFactory;
    private final CustomLyOrderInfoMapper customLyOrderInfoMapper;

    @Autowired
    public DbBatchOperation(SqlSessionFactory sqlSessionFactory, CustomLyOrderInfoMapper customLyOrderInfoMapper) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.customLyOrderInfoMapper = customLyOrderInfoMapper;
    }


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
     * 测试在大数量下时，openSession为Batch模式下如果要获取每笔结果该怎么优化
     * <p>
     * flushStatements 和commit区别：flushStatements只是执行，不会真正提交。commit是提交（会先执行flushStatements再提交）
     *
     * 看起来是快速和获取每笔的结果不可兼得啊
     *
     * @param lyOrderInfoList infoList
     */
    public void batchInsert(List<LyOrderInfo> lyOrderInfoList) {
        batchInsert2(lyOrderInfoList);
    }

    /**
     * 开启批量模式，简单commit
     * 好处：可以立刻获得每条执行结果
     * 坏处：非常慢
     *
     * @param lyOrderInfoList info
     */
    private void batchInsert1(List<LyOrderInfo> lyOrderInfoList) {
        long start = System.currentTimeMillis();
        // note 开了BATCH后，必须commit, 否则等于没提交不生效
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, true)) {
            CustomLyOrderInfoMapper batchMapper = sqlSession.getMapper(CustomLyOrderInfoMapper.class);
            for (LyOrderInfo lyOrderInfo : lyOrderInfoList) {
                try {
                    batchMapper.insert(lyOrderInfo);
                    sqlSession.commit();
                } catch (DuplicateKeyException duplicateKeyException) {
                    log.error("重复数据,id:{}", lyOrderInfo.getOrderSerial());
                } catch (Exception e) {
                    log.error("未知异常,id:{}", lyOrderInfo.getOrderSerial());
                    // 这里异常不管
                    // throw new RuntimeException(e);
                }
            }
        }
        log.info("本次插入数据{}条，共耗时{}ms", lyOrderInfoList.size(), System.currentTimeMillis() - start);
    }

    /**
     * 开启批量模式，500条执行一次，最后commit
     * 好处：非常快
     * 坏处：无法获得每条sql执行结果
     *
     * @param lyOrderInfoList info
     */
    private void batchInsert2(List<LyOrderInfo> lyOrderInfoList) {
        long start = System.currentTimeMillis();
        int batchSize = 500;
        int i = 0;
        // note 开了BATCH后，必须commit, 否则等于没提交不生效
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, true)) {
            CustomLyOrderInfoMapper batchMapper = sqlSession.getMapper(CustomLyOrderInfoMapper.class);
            for (LyOrderInfo lyOrderInfo : lyOrderInfoList) {
                if (i++ % batchSize == 0) {
                    sqlSession.flushStatements();
                }
                batchMapper.insert(lyOrderInfo);
            }
            sqlSession.commit();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        log.info("本次插入数据{}条，共耗时{}ms", lyOrderInfoList.size(), System.currentTimeMillis() - start);
    }

    /**
     * 测试大数据情况下，for循环更新，批量case when，update by select三种哪种更新效率更高
     * 时间上case when ≈ update by select < for
     * 从执行计划上看，不用连表的case when更优（索引什么都一样的情况下）
     *
     * @param lyOrderInfoList orderList
     */
    public void batchUpdate(List<LyOrderInfo> lyOrderInfoList) {
        log.info("开始更新");
        long start = System.currentTimeMillis();
        updateByMergeInto(lyOrderInfoList);
        log.info("更新{}笔，耗时{}ms", lyOrderInfoList.size(), System.currentTimeMillis() - start);
    }

    /**
     * 简单for循环更新
     * @param lyOrderInfoList info
     */
    private void simpleUpdate(List<LyOrderInfo> lyOrderInfoList) {
        for (LyOrderInfo lyOrderInfo : lyOrderInfoList) {
            customLyOrderInfoMapper.updateByPrimaryKey(lyOrderInfo);
        }
    }

    /**
     * 通过case when
     *
     * @param lyOrderInfoList info
     */
    private void updateByCaseWhen(List<LyOrderInfo> lyOrderInfoList) {
        List<List<LyOrderInfo>> splitInfo = CollUtil.split(lyOrderInfoList, 1000);
        splitInfo.forEach(customLyOrderInfoMapper::updateRvcNoBySerialList);
    }

    /**
     * 通过merge into
     *
     * @param lyOrderInfoList info
     */
    private void updateByMergeInto(List<LyOrderInfo> lyOrderInfoList) {
        List<List<LyOrderInfo>> splitInfo = CollUtil.split(lyOrderInfoList, 1000);
        splitInfo.forEach(customLyOrderInfoMapper::updateRvcNoByOtherTable);
    }
}
