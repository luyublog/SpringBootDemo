package com.east.demo.service.commonrecord.mybatis;

import com.east.demo.persist.entity.LyUserInfo;
import com.east.demo.persist.mapper.LyUserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * 详解mapper参数配置: https://cloud.tencent.com/developer/article/1551940
 */
@SpringBootTest
@Slf4j
class MybatisTest {
    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    void setUp() throws IOException {
        //指定mybatis全局配置文件
//        String resource = "mybatis/mybatis-config.xml";
//        //读取全局配置文件
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        //构建SqlSessionFactory对象
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        this.sqlSessionFactory = sqlSessionFactory;
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void getName() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            LyUserInfoMapper userMapper = sqlSession.getMapper(LyUserInfoMapper.class);
            LyUserInfo userModel = userMapper.getByName("Jack");
//            log.info(userModel.toString());
            System.out.println(userModel);
        }
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