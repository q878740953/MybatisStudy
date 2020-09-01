package com.mybatis.test;

import com.mybatis.dao.AccountMapper;
import com.mybatis.dao.RoleMapper;
import com.mybatis.dao.UserMapper;
import com.mybatis.domin.Account;
import com.mybatis.domin.Role;
import com.mybatis.domin.User;
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
        inputStream = Resources.getResourceAsStream("mybatis-config");
        sqlSession = new SqlSessionFactoryBuilder().build(inputStream).openSession();
    }
    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }
    @Test
    public void getAllAccount(){
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accounts = mapper.getAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
    @Test
    public void getAllAccountAndUser(){
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accounts = mapper.getAllAccountAndUser();
        for (Account account : accounts) {
            System.out.println(account);

        }
    }
    @Test
    public void getAllUser(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void getAllRole(){
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        List<Role> roles = mapper.getAllRole();
        for (Role role : roles) {
            System.out.println(role);
        }
    }
}
