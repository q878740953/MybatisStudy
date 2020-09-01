package com.mybatis.test;

import com.mybatis.dao.UserDao;
import com.mybatis.domain.User;
import com.mybatis.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MybatisTest {
    @Test
    public void getUser(){
        // 使用SqlSession创建Dao的接口对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        // 使用代理对象指向方法
        List<User> users = mapper.getUser();
        for (User user : users) {
            System.out.println(user);
        }
        // 释放资源
        sqlSession.close();
    }
}
