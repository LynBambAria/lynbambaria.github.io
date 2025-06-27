package com.liu.covid.controller;

import com.liu.covid.entity.User;
import com.liu.covid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录与注册控制器
 */
@RestController
@RequestMapping("/userlogin")
public class LoginController {

    @Autowired
    UserService userService; // 用户服务，用于处理登录和注册逻辑

    /**
     * 用户登录接口
     * @param loginform 用户登录信息
     * @return 登录结果的消息：成功或失败
     */
    @PostMapping("/user")
    public String login(@RequestBody User loginform) {
        // 调用 userService 的 login 方法进行登录验证
        String message = userService.login(loginform);
        return message; // 返回登录结果消息
    }

    /**
     * 用户注册接口
     * @param reUser 用户注册信息
     * @return 注册结果的消息：成功或失败
     */
    @PostMapping("/register")
    public String register(@RequestBody User reUser) {
        // 调用 userService 的 register 方法进行用户注册
        String message = userService.register(reUser);
        return message; // 返回注册结果消息
    }
}
