package com.liu.covid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.covid.entity.Department;
import com.liu.covid.entity.EmpIden;
import com.liu.covid.mapper.DepartMapper;
import com.liu.covid.mapper.EmpIdenMapper;
import com.liu.covid.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * DepartServiceImpl 是 DepartService 接口的实现类，用于处理部门相关的业务逻辑。
 */
@Service
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Department> implements DepartService {

    @Autowired
    private DepartMapper mapper;  // 自动注入 DepartMapper，用于操作部门数据表

    /**
     * 获取所有部门的名称
     * @return List<String> 包含所有部门名称的列表
     */
    @Override
    public List<String> getAll() {
        List<Department> list;  // 用于存储从数据库中查询到的所有部门信息
        List<String> name = new ArrayList<>();  // 用于存储所有部门名称的列表
        list = mapper.selectList(null);  // 查询所有部门记录
        for (Department de : list) {  // 遍历查询到的部门记录
            name.add(de.getName());  // 将部门名称添加到 name 列表中
        }
        return name;  // 返回部门名称列表
    }
}
