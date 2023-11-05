package com.east.demo.service.commonrecord.mybatis;

import com.east.demo.persist.entity.LyEmployeeInfo;
import com.east.demo.persist.mapper.LyEmployeeInfoMapper;
import com.east.demo.persist.mapper.LySequenceMapper;
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
            LyEmployeeInfoMapper userMapper = sqlSession.getMapper(LyEmployeeInfoMapper.class);
            LyEmployeeInfo userModel = userMapper.getByName("Steven");
            sqlSession.clearCache();
            LyEmployeeInfo userModel2 = userMapper.getByName("Steven");


            log.info("{}", userModel == userModel2);
        }
    }

    @Test
    public void getNameByMap() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            LyEmployeeInfoMapper userMapper = sqlSession.getMapper(LyEmployeeInfoMapper.class);
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("firstName", "Steven");
            stringObjectMap.put("lastName", "King");
            LyEmployeeInfo userModel = userMapper.getByMap(stringObjectMap);
            log.info("{}", userModel);
        }
    }

    @Test
    public void getNameByFullNameTest() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            LyEmployeeInfoMapper userMapper = sqlSession.getMapper(LyEmployeeInfoMapper.class);
            LyEmployeeInfo userModel = userMapper.getByFullName("Steven", "King", "AD_PRES");
            log.info("{}", userModel);
        }
    }

    /**
     * 指定Param，不受编译环境影响
     */
    @Test
    public void getNameByFullNameTest2() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            LyEmployeeInfoMapper userMapper = sqlSession.getMapper(LyEmployeeInfoMapper.class);
            LyEmployeeInfo userModel = userMapper.getByFullName2("Steven", "King", "AD_PRES");
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
            LyEmployeeInfoMapper mapper = sqlSession.getMapper(LyEmployeeInfoMapper.class);
            LyEmployeeInfo info = mapper.getInfoWithJobByFirstName("Steven");
            log.info("{}", info);
        }
    }

    @Test
    public void getNameBySpecificTest() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            LyEmployeeInfoMapper userMapper = sqlSession.getMapper(LyEmployeeInfoMapper.class);
            LyEmployeeInfo userModel = userMapper.getBySpecific(
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
            LyEmployeeInfoMapper userMapper = sqlSession.getMapper(LyEmployeeInfoMapper.class);
            LyEmployeeInfo userModel = userMapper.getByName("Steven");
//            sqlSession.clearCache();
            LyEmployeeInfo userModel2 = userMapper.getByName("Steven");


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
        LyEmployeeInfo userModelList1;
        LyEmployeeInfo userModelList2;
        LyEmployeeInfo userModelList3;
        try (SqlSession sqlSession1 = this.sqlSessionFactory.openSession(true);) {
            LyEmployeeInfoMapper mapper = sqlSession1.getMapper(LyEmployeeInfoMapper.class);
            userModelList1 = mapper.getByName("Steven");
            log.info("{}", userModelList1);
        }
        try (SqlSession sqlSession2 = this.sqlSessionFactory.openSession(true);) {
            LyEmployeeInfoMapper mapper = sqlSession2.getMapper(LyEmployeeInfoMapper.class);
            userModelList2 = mapper.getByName("Steven");
            log.info("{}", userModelList2);
        }
        try (SqlSession sqlSession2 = this.sqlSessionFactory.openSession(true);) {
            LyEmployeeInfoMapper mapper = sqlSession2.getMapper(LyEmployeeInfoMapper.class);
            userModelList3 = mapper.getByName("Steven");
            log.info("{}", userModelList3);
        }

        log.info("userModelList1==userModelList2: {}", userModelList1 == userModelList2);
        log.info("userModelList2==userModelList3: {}", userModelList2 == userModelList3);
    }

    public void init() {
//        String resource="mybatis-config.xml";
//        InputStream inputStream=null;
//        try {
//            inputStream = Resources.getResourceAsStream(resource);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        SqlSessionFactory sqlSessionFactory=null;
//        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession=null;
//        try {
//            sqlSession=sqlSessionFactory.openSession();
//            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
//            Role role=roleMapper.getRole(1L);
//            System.out.println(role.getId()+":"+role.getRoleName()+":"+role.getNote());
//            sqlSession.commit();
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            sqlSession.rollback();
//            e.printStackTrace();
//        }finally {
//            sqlSession.close();
//        }
    }

}