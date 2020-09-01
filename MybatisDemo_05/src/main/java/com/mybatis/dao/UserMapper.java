package com.mybatis.dao;

import com.mybatis.domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> getAllUser();
    public User getUserById(int id);
    public void updateUserInfo(User user);
}
