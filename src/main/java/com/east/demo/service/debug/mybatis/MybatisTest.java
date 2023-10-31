package com.east.demo.service.debug.mybatis;

/**
 * @author: east
 * @date: 2023/10/31
 */

public class MybatisTest {
//    public static void main(String[] args) {
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
//    }
}
