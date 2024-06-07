package com.example.mapper;

import com.example.domain.Good;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GoodMapper {


    @Select("select * from t_good where id = #{goodId}")
    Good findGoodById(int goodId);

    @Update("update t_good set good_stock = good_stock - #{stockNum} where id = #{goodId}")
    void reduceStock(@Param("goodId") int goodId,@Param("stockNum") int stockNum);
}
