package com.example.abc;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface ABCMapper {
    /**
     * 账户余额减钱
     * @param userId 用户ID
     * @param money 本次扣减的钱
     */
    @Update("update user set money=money-#{money} where id =#{userId}")
    void reduceMoney(@Param("userId") int userId, @Param("money") double money);
}