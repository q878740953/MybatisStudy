package com.mybatis.dao;

import com.mybatis.domin.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountMapper {
    /**
     * 一对一查询，查询出所有账户的信息的同时，查询出该账户下的用户信息
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMapper", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            @Result(property = "user", column = "uid", one = @One(select = "com.mybatis.dao.UserMapper.getUserById", fetchType = FetchType.EAGER))
    })
    List<Account> getAllAccountAndUser();
    @Select("select * from account where uid=#{uid}")
    List<Account> getAccountByUid();
}
