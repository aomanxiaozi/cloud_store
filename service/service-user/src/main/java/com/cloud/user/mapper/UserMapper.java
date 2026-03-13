package com.cloud.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.user.entity.User;
import com.store.test.bean.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
    @Insert("insert into test(id, value) values(#{id}, #{value})")
    void insertTest(Test test);
}
