package com.mybatis.dao;

import com.mybatis.domin.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select * from user")
    public List<User> getAllUser();

    /**
     * 根据id查询用户信息
     * @param id 输入需要查询的id
     * @return 返回对应的用户信息
     */
    @Select("select * from user where id=#{id}")
    User getUserById(int id);
    @Delete("delete from user where id =#{id}")
    void delUser(int id);

    /**
     * 模糊查询
     * @param username
     * @return
     */
    @Select("select * from user where username like #{value}")
    List<User> getUserByLike(String username);
    @Update("update user set username=#{username}, sex=#{sex} where id=#{id}")
    void updateUser(User user);
    @Insert("insert into user(username, sex, birthday, address) values(#{username}, #{sex}, #{birthday}, #{address})")
    void insertUser(User user);
    /**
     * 一对多查询，查询用户的同时，查询出该用户下所有的账户信息
     */
    @Select("select * from user")
    @Results(id = "userMapper",value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "address", column = "address"),
            @Result(property = "accounts", column = "id", many = @Many(select = "com.mybatis.dao.AccountMapper.getAccountByUid", fetchType = FetchType.LAZY)),
    })
    List<User> getUserAndAccount();
}
