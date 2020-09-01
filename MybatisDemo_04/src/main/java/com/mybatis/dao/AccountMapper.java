package com.mybatis.dao;

import com.mybatis.domin.Account;

import java.util.List;

public interface AccountMapper {
    public List<Account> getAllAccount();
    public List<Account> getAllAccountAndUser();
}
