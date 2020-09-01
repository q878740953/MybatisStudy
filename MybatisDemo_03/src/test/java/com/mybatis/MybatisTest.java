package com.mybatis;

import com.mybatis.dao.UserMapper;
import com.mybatis.domain.QueryUser;
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
import java.util.ArrayList;
import java.util.List;

public class MybatisTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    @Before
    public void init() throws IOException {
        // 读取配置文件
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 获取sqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }
    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        inputStream.close();
        sqlSession.close();
    }
    @Test
    public void finUserByCondition(){
        User user = new User();
        user.setName("张三");
        user.setGender("女");
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findUserByCondition(user);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
    @Test
    public void findUserInIds(){
        QueryUser queryUser = new QueryUser();
        List<Integer> list = new ArrayList<>();
        list.add(40);
        list.add(21);
        list.add(71);
        queryUser.setIds(list);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findUserInIds(queryUser);
        for (User user : users) {
            System.out.println(user);
        }

    }
}
