package com.cloud.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.user.entity.User;
import com.store.common.R;

public interface UserService extends IService<User> {
    String test();

    boolean login(User user);

    R<User> register(User user);
}
