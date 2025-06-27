package com.liu.covid.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.covid.entity.EmpIden;
import com.liu.covid.entity.User;
import com.liu.covid.mapper.EmpIdenMapper;
import com.liu.covid.mapper.UserMapper;
import com.liu.covid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * UserServiceImpl 是 UserService 接口的实现类，主要用于用户登录和注册的业务逻辑处理。
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper mapper;  // 自动注入 UserMapper，用于操作用户数据表

    /**
     * 用户登录
     * @param user 用户登录信息，包含用户名和密码
     * @return 登录结果的字符串：成功返回 "success"，失败返回 "error"
     */
    @Override
    public String login(User user) {
        // 创建查询条件，根据用户名查询用户
        QueryWrapper<User> userQueryWrapper = Wrappers.query();
        userQueryWrapper.like("username", user.getUsername());

        // 查询用户列表
        List<User> list = mapper.selectList(userQueryWrapper);

        // 如果用户存在
        if (list.size() != 0) {
            // 对用户输入的密码进行MD5加密
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            // 比较加密后的密码与数据库中的密码是否一致
            if (list.get(0).getPassword().equals(password)) {
                return "success";  // 密码匹配，登录成功
            } else {
                return "error";  // 密码不匹配，登录失败
            }
        } else {
            return "error";  // 用户不存在，登录失败
        }
    }

    /**
     * 用户注册
     * @param user 用户注册信息，包含用户名和密码等
     * @return 注册结果的字符串："success"表示成功，"error"表示失败，"repeat"表示用户名重复
     */
    @Override
    public String register(User user) {
        // 判断用户信息是否为空
        if (user != null) {
            boolean flag = true;
            // 检查用户名是否已经存在
            for (User list : mapper.selectList(null)) {
                if (list.getUsername().equals(user.getUsername())) {
                    flag = false;  // 如果用户名已存在，则标记为 false
                    break;
                }
            }

            // 如果用户名没有重复
            if (flag) {
                // 对用户输入的密码进行MD5加密
                String pw = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
                user.setPassword(pw);  // 设置加密后的密码

                // 插入用户数据
                int index = mapper.insert(user);

                // 判断插入是否成功
                if (index == 1) {
                    return "success";  // 注册成功
                } else {
                    return "error";  // 注册失败
                }
            } else {
                return "repeat";  // 用户名重复，不能注册
            }
        } else {
            return "error";  // 用户信息为空，注册失败
        }
    }
}
