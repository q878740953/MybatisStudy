package com.mybatis.test;

import com.mybatis.dao.UserMapper;
import com.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    private SqlSession sqlSession;
    private InputStream inputStream;
    @Before
    public void init() throws Exception {
        // 读取配置文件xml
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 创建SqlSession
        sqlSession = sqlSessionFactory.openSession();
    }
    @After
    public void destroy() throws IOException {
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
        inputStream.close();
    }
    /**
     * 查询所有用户信息
     */
    @Test
    public void getAllUser(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> allUser = mapper.getAllUser();
        for (User user : allUser) {
            System.out.println(user);
        }
    }
    @Test
    public void saveUser(){
        User user = new User();
        user.setName("益张达");
        user.setGender("男");
        user.setAge(24);
        user.setAddress("哈哈哈哈哈哈哈哈");
        user.setQq("878740953");
        user.setEmail("878740953@qq.com");
        System.out.println("保存之前：" + user);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.saveUser(user);
        System.out.println("保存之后：" + user);
    }
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(70);
        user.setName("益张达");
        user.setGender("男");
        user.setAge(23);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser(user);
    }
    @Test
    public void deleteUser(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser(66);
    }
    @Test
    public void findById(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findById(65);
        System.out.println(user);
    }
    @Test
    public void findByName(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findByName("%张%");
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void getTotal(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int total = mapper.getTotal();
        System.out.println(total);
    }
}
