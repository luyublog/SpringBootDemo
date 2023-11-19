package com.east.demo.service.commonrecord.mybatis;

import com.east.demo.model.dto.test.LyEmployeeInfoDTO;
import com.east.demo.persist.entity.LyJobInfo;
import com.east.demo.persist.entity.base.LyEmployeeInfo;
import com.east.demo.persist.mapper.LyJobInfoMapper;
import com.east.demo.persist.mapper.LySequenceMapper;
import com.east.demo.persist.mapper.custom.CustomLyEmployeeInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 详解mybatis:
 * <a href="http://itsoku.com/course/4/82">详解mybatis</a>
 */
@Slf4j
class MybatisTest {
    public SqlSessionFactory sqlSessionFactory;
    private Long startTime;

    @BeforeEach
    void setUp() throws IOException {
//        指定mybatis全局配置文件
        String resource = "mybatis/mybatis-config.xml";
        //读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sqlSessionFactory;
        startTime = System.currentTimeMillis();
    }

    @AfterEach
    void tearDown() {
        log.info("总耗时：{}ms", System.currentTimeMillis() - startTime);
    }


    @Test
    public void getNameByString() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            CustomLyEmployeeInfoMapper userMapper = sqlSession.getMapper(CustomLyEmployeeInfoMapper.class);
            LyEmployeeInfoDTO userModel = userMapper.getByName("Steven");
            sqlSession.clearCache();
            LyEmployeeInfoDTO userModel2 = userMapper.getByName("Steven");


            log.info("{}", userModel == userModel2);
        }
    }

    @Test
    public void getNameByMap() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            CustomLyEmployeeInfoMapper userMapper = sqlSession.getMapper(CustomLyEmployeeInfoMapper.class);
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("firstName", "Steven");
            stringObjectMap.put("lastName", "King");
            LyEmployeeInfoDTO userModel = userMapper.getByMap(stringObjectMap);
            log.info("{}", userModel);
        }
    }

    @Test
    public void getNameByFullNameTest() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            CustomLyEmployeeInfoMapper userMapper = sqlSession.getMapper(CustomLyEmployeeInfoMapper.class);
            LyEmployeeInfoDTO userModel = userMapper.getByFullName("Steven", "King", "AD_PRES");
            log.info("{}", userModel);
        }
    }

    /**
     * 指定Param，不受编译环境影响
     */
    @Test
    public void getNameByFullNameTest2() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            CustomLyEmployeeInfoMapper userMapper = sqlSession.getMapper(CustomLyEmployeeInfoMapper.class);
            LyEmployeeInfoDTO userModel = userMapper.getByFullName2("Steven", "King", "AD_PRES");
            log.info("{}", userModel);
        }
    }

    /**
     * 获取队列
     */
    @Test
    public void selectSequenceTest() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            LySequenceMapper mapper = sqlSession.getMapper(LySequenceMapper.class);
            Long sequence = mapper.selectSequence();
            log.info("{}", sequence);
        }
    }

    /**
     * 获取队列
     */
    @Test
    public void selectSequenceByNameTest() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            LySequenceMapper mapper = sqlSession.getMapper(LySequenceMapper.class);
            Long sequence = mapper.selectSequenceByName("HR.EMPLOYEES_SEQ");
            log.info("{}", sequence);
        }
    }

    /**
     * xml中通过associate标签来连续查询
     */
    @Test
    public void selectInfoByAssociationInXmlTest() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            CustomLyEmployeeInfoMapper mapper = sqlSession.getMapper(CustomLyEmployeeInfoMapper.class);
            LyEmployeeInfoDTO info = mapper.getInfoWithJobByFirstName("Steven");
            log.info("{}", info);
        }
    }

    @Test
    public void getNameBySpecificTest() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            CustomLyEmployeeInfoMapper userMapper = sqlSession.getMapper(CustomLyEmployeeInfoMapper.class);
            LyEmployeeInfoDTO userModel = userMapper.getBySpecific(
                    "Steven", "King", "", Collections.singletonList("AD_PRES"));
            log.info("{}", userModel);
        }
    }

    /**
     * 一级缓存
     * 缓存失效方法：
     * 1. 增删改
     * 2. sqlSession.clearCache()
     * 3. xml上指定flushCache=true
     */
    @Test
    public void cacheTest() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            CustomLyEmployeeInfoMapper userMapper = sqlSession.getMapper(CustomLyEmployeeInfoMapper.class);
            LyEmployeeInfoDTO userModel = userMapper.getByName("Steven");
//            sqlSession.clearCache();
            LyEmployeeInfoDTO userModel2 = userMapper.getByName("Steven");


            log.info("{}", userModel == userModel2);
        }
    }

    /**
     * 二级缓存相关
     * 开启：全局配置+具体xml中添加cache标签+对象实现序列化接口
     * 与一级区别： 不是指向内存中的同一个对象
     */
    @Test
    public void level2CacheTest() {
        LyEmployeeInfoDTO userModelList1;
        LyEmployeeInfoDTO userModelList2;
        LyEmployeeInfoDTO userModelList3;
        try (SqlSession sqlSession1 = this.sqlSessionFactory.openSession(true);) {
            CustomLyEmployeeInfoMapper mapper = sqlSession1.getMapper(CustomLyEmployeeInfoMapper.class);
            userModelList1 = mapper.getByName("Steven");
            log.info("{}", userModelList1);
        }
        try (SqlSession sqlSession2 = this.sqlSessionFactory.openSession(true);) {
            CustomLyEmployeeInfoMapper mapper = sqlSession2.getMapper(CustomLyEmployeeInfoMapper.class);
            userModelList2 = mapper.getByName("Steven");
            log.info("{}", userModelList2);
        }
        try (SqlSession sqlSession2 = this.sqlSessionFactory.openSession(true);) {
            CustomLyEmployeeInfoMapper mapper = sqlSession2.getMapper(CustomLyEmployeeInfoMapper.class);
            userModelList3 = mapper.getByName("Steven");
            log.info("{}", userModelList3);
        }

        log.info("userModelList1==userModelList2: {}", userModelList1 == userModelList2);
        log.info("userModelList2==userModelList3: {}", userModelList2 == userModelList3);
    }

    /**
     * resultMap失效测试方法
     */
    @Test
    public void resultMapExceptionTest() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            LyJobInfoMapper mapper = sqlSession.getMapper(LyJobInfoMapper.class);
            LyJobInfo testJob = mapper.getByJobId("TEST_JOB");
            log.info(testJob.toString());
        }
    }

    @Test
    public void customMapperTest() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            CustomLyEmployeeInfoMapper userMapper = sqlSession.getMapper(CustomLyEmployeeInfoMapper.class);
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("firstName", "Steven");
            stringObjectMap.put("lastName", "King");
            LyEmployeeInfoDTO userModel = userMapper.getByMap(stringObjectMap);
            log.info("{}", userModel);

            LyEmployeeInfo lyEmployeeInfo = userMapper.selectByPrimaryKey(100);
            log.info(lyEmployeeInfo.toString());
        }
    }

}