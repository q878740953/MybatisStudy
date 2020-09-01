package com.mybatis.test;

import com.mybatis.dao.AccountMapper;
import com.mybatis.dao.UserMapper;
import com.mybatis.domain.Account;
import com.mybatis.domain.User;
import com.sun.security.jgss.GSSUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    @Before
    public void init() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSession = new SqlSessionFactoryBuilder().build(inputStream).openSession();
    }
    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }
    @Test
    public void getAllUser(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getAllUser();
//        for (User user : users) {
//            System.out.println(user);
//        }
    }
    @Test
    public void getAllAccount(){
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accounts = mapper.getAllAccount();
//        for (Account account : accounts) {
//            System.out.println(account);
//        }

    }
    @Test
    public void getUserById(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.getUserById(41);
//        user1.setUsername("我更新了数据，清空缓存");
//        mapper.updateUserInfo(user1);
        System.out.println(user1);
        User user2 = mapper.getUserById(41);
        System.out.println(user2);
        System.out.println(user1 == user2);

    }
}

