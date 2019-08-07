package com.hz.testgit.dao;

import com.hz.testgit.bean.Order;
import com.hz.testgit.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface UserDao {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    public User queryUserById(String id);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<User> queryUserAll();

    /**
     * 新增用户
     *
     * @param user
     */
    public void insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * 根据id删除用户信息
     *
     * @param id
     */
    public void deleteUser(String id);

    /**
     * 根据姓名模糊查询
     * @param name
     * @return
     */
    public List<User> queryUserInfoByName(@Param("name") String name);

    /**
     * 根据年龄和姓名查询
     * @param name
      * @param age
     * @return
     */
    public List<User> queryUserInfoByNameAndAge(@Param("name") String name,@Param("age") String age);


    /**
     *
     * @param u
     * @return
     */
    public List<User> queryUserListByNameAndAge(User u);

    /**
     *
     * @return
     */
    public List<User> queryUserInfoByList(@Param("ids") String []str);


    User queryUserInfo(int id);


    public Order selectByPrimaryKey(int id);


}
