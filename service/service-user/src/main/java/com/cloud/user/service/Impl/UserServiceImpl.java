package com.cloud.user.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.user.entity.User;
import com.cloud.user.mapper.UserMapper;
import com.cloud.user.service.UserService;
import com.store.common.R;
import com.store.test.bean.Test;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    public UserMapper userMapper;
    @Override
    @GlobalTransactional
    public String test() {
        Test test=new Test();
        test.setId(UUID.randomUUID().toString().substring(0,3));
        test.setValue("value");
        userMapper.insertTest(test);
        return "成功测试了";
    }

    @Override
    public boolean login(User user) {
        return true;
    }

    @Override
    public R<User> register(User user) {
        return null;
    }

}
