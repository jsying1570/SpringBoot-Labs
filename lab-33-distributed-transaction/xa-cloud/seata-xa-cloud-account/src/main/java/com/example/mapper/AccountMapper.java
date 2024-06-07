package com.example.mapper;

import com.example.domain.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AccountMapper {

    @Select("select * from t_account where id = #{accountId}")
    Account findAccountById(int accountId);


    @Update("update t_account set money = money - #{money} where id = #{accountId}")
    void reduceMoney(@Param("money") double money, @Param("accountId") int accountId);

}
