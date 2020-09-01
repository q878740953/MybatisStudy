package com.mybatis.dao;

import com.mybatis.domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> getAllUser();
    public void saveUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
    public User findById(int id);
    public List<User> findByName(String name);

    public int getTotal();
}
