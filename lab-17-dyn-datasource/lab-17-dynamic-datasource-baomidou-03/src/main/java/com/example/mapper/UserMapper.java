package com.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@DS("abc")
public interface UserMapper extends BaseMapper<UserDO> {

    UserDO selectById(@Param("id") Integer id);

}
