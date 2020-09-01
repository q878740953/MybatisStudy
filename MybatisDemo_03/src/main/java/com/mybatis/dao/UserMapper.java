package com.mybatis.dao;

import com.mybatis.domain.QueryUser;
import com.mybatis.domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> findUserByCondition(User user);
    public List<User> findUserInIds(QueryUser queryUser);
}
