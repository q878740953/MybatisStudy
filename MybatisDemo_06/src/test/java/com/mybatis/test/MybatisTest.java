package com.mybatis.test;

import com.mybatis.dao.AccountMapper;
import com.mybatis.dao.UserMapper;
import com.mybatis.domin.Account;
import com.mybatis.domin.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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
    public void getAllUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void getUserById() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(41);
        System.out.println(user);
    }

    @Test
    public void delUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.delUser(50);
        System.out.println("删除成功");
    }

    @Test
    public void getUserByLike() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUserByLike("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void updateUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(41);
        user.setUsername("我叫张益达");
        user.setSex("男");
        mapper.updateUser(user);
        System.out.println("更新成功");
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setUsername("张益达");
        user.setSex("女");
        user.setAddress("安徽");
        user.setBirthday(new Date());
        sqlSession.getMapper(UserMapper.class).insertUser(user);

        System.out.println("插入成功");
    }

    @Test
    public void getAccountAndUser() {
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accounts = mapper.getAllAccountAndUser();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void getUserAndAccount() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUserAndAccount();
//        for (User user : users) {
//            System.out.println(user);
//        }
    }
}
