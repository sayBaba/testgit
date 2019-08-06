package com.hz.testgit.bean;

import com.hz.testgit.dao.UserDao;
//import com.hz.testgit.dao.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;

public class UserDaoTest {

    public UserDao userDao;
    public SqlSession sqlSession;

    @Before
    public void setUp() throws Exception{
        // mybatis-config.xml
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream is = Resources.getResourceAsStream(resource);
        // 构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 获取sqlSession
        sqlSession = sqlSessionFactory.openSession();
//        this.userDao = new UserDaoImpl(sqlSession);
        this.userDao = sqlSession.getMapper(UserDao.class);

    }

    @Test
    public void testQueryUserById(){
        User user = userDao.queryUserById("1");
        System.out.println("============user=========="+user);

    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setId("3");
        user.setBirthday(new Date());
        user.setAge(18);
        user.setCreated("666");
        user.setName("小张");
        user.setPassword("123");
        user.setSex(1);
        user.setUpdated("11111");
        user.setUserName("小小张");
        userDao.insertUser(user);
        sqlSession.commit();

    }


}
