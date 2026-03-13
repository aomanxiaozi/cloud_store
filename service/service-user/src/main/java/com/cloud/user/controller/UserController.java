package com.cloud.user.controller;

import com.cloud.user.Util.JwtUtil;
import com.cloud.user.entity.User;
import com.cloud.user.service.UserService;
import com.store.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    public JwtUtil jwtUtil;
    @GetMapping("/test")
    public String Test(){
        return userService.test();
    }
    @PostMapping("/login")
    public R<String> Login(@RequestBody User user){
        if(userService.login(user)){
            String token=jwtUtil.generateToken(user);
            return R.success(token,"登陆成功");
        }
        return R.error("登陆失败");
    }
    @PostMapping("/register")
    public R<User> Register(@RequestBody User user){

        return  userService.register(user);
    }
}
