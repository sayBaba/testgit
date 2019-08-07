package com.hz.testgit.bean;

import com.hz.testgit.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

//import com.hz.testgit.dao.UserDaoImpl;

public class UserDaoTest {

    public UserDao userDao;
    public SqlSession sqlSession;
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception{
        // mybatis-config.xml
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream is = Resources.getResourceAsStream(resource);
        // 构建SqlSessionFactory
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 获取sqlSession
         sqlSession = sqlSessionFactory.openSession();
//        this.userDao = new UserDaoImpl(sqlSession);

        this.userDao = sqlSession.getMapper(UserDao.class);

    }

    @Test
    public void testQueryUserById(){
        User user = userDao.queryUserById("1");
        System.out.println("============user=========="+user);
//        sqlSession.clearCache();

        User user2 = new User();
        user2.setId("3");
        user2.setName("小张小");
        user2.setPassword("456");
        userDao.updateUser(user2);

        User user1 = userDao.queryUserById("1");
        System.err.println("============user1=========="+user1);



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

    @Test
    public void testQueryUserInfoByName(){
        List<User> list = userDao.queryUserInfoByName("小");
        if(list.size()>0){
            System.out.println("==========="+list.get(0));

        }
    }

    @Test
    public void testQueryUserInfoByNameAndAge(){
        List<User> list =  userDao.queryUserInfoByNameAndAge("","");
        if(list.size()>0){
            System.out.println("==========="+list.get(0));
        }
    }

    @Test
    public void testQueryUserListByNameAndAge(){
        User u = new User();
        u.setName("小");
        u.setAge(18);
        List<User> list =  userDao.queryUserListByNameAndAge(u);
        if(list.size()>0){
            System.out.println("==========="+list.get(0));
        }
    }

    @Test
    public void testQueryUserInfoByList(){
        String []str = {"1","2","3"};
        List<User> list =  userDao.queryUserInfoByList(str);

        for (int i =0;i<list.size();i++){
            System.err.println(list.get(i));
        }

    }

    @Test
    public void testCache(){
        User user = userDao.queryUserById("1");
        System.out.println("============user=========="+user);

        //关闭session，防止查询一级缓存
        sqlSession.close();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        this.userDao = sqlSession.getMapper(UserDao.class);

        User user1 = userDao.queryUserById("1");
        System.out.println("============user1=========="+user1);
    }

    @Test
    public void testLayzyLoad(){
        User user = userDao.queryUserInfo(1);
//        System.out.println(list.get(0).getOrder());
//        sqlSession.clearCache();

//        Order o = user.getOrder();
//        System.out.println("-----------"+ o.getOrderId());
    }

}
