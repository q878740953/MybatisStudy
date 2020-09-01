package com.mybatis.dao;

import com.mybatis.domain.Account;

import java.util.List;

public interface AccountMapper {
    public List<Account> getAccountByUid(int uid);
    public List<Account> getAllAccount();
}
