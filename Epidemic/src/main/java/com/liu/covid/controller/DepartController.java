package com.liu.covid.controller;


import com.liu.covid.entity.Department;
import com.liu.covid.mapper.DepartMapper;
import com.liu.covid.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// 控制器类，用于处理与部门相关的请求
@RestController
@RequestMapping("/depart")
public class DepartController {

    // 自动注入 DepartService 服务
    @Autowired
    DepartService service;

    // 处理 GET 请求，返回所有部门信息的列表// 处理 GET 请求，返回所有部门信息的列表
    @GetMapping("/findAll")
    private List<String>  findAll(){
       return service.getAll();
    }// 调用服务层方法获取所有部门信息
}
