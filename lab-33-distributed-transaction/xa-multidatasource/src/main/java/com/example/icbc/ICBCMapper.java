package com.example.icbc;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface ICBCMapper {
    /**
     * 账户余额加钱
     * @param userId 用户ID
     * @param money 钱
     */
    @Update("update user set money=money+#{money} where id =#{userId}")
    void increaseMoney(@Param("userId") int userId, @Param("money") double money);
}