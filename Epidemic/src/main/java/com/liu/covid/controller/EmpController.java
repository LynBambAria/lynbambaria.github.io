package com.liu.covid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.covid.entity.EmpHealth;
import com.liu.covid.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// 控制器类，用于处理与员工健康信息相关的请求
@RestController
@RequestMapping("/emp")
public class EmpController {
    // 自动注入 EmpMapper 对象
    @Autowired
    private EmpMapper mapper;

    // 分页查询员工健康信息
    @GetMapping("/findAll/{page}/{size}")
    public Page<EmpHealth> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        // 创建查询条件，按创建时间降序排列
        QueryWrapper<EmpHealth> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("createTime");
        // 创建分页对象
        Page<EmpHealth> page1= new Page<>(page,size);
        // 执行分页查询并返回结果
        Page<EmpHealth> result=mapper.selectPage(page1,wrapper).addOrder();
        return result;
    }

    // 保存员工健康信息
    @PostMapping("/save")
    public String save(@RequestBody EmpHealth emp){
        // 插入数据并返回操作结果
        int result = mapper.insert(emp);
        if (result==1){
            return "success";
        }else {
            return "error";
        }
    }

    // 根据员工 ID 查询健康信息
    @GetMapping("/findById/{id}")
    public EmpHealth findById(@PathVariable("id") Integer id){
        return mapper.selectById(id);
    }// 根据 ID 查询数据并返回

    // 更新员工健康信息
    @PutMapping("/update")
    public String update(@RequestBody EmpHealth emp){
        // 更新数据并返回操作结果
        int result=mapper.updateById(emp);
        if (result==1){
            return "success";
        }else {
            return "error";
        }
    }

    // 根据员工 ID 删除健康信息
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id")Long id){
        mapper.deleteById(id+"L");
    }// 删除指定 ID 的数据

    // 根据指定字段和内容搜索员工健康信息
    @GetMapping("/search/{searchkey}/{stext}")
    public List<EmpHealth> search(@PathVariable("searchkey")String searchkey, @PathVariable("stext")String stext){
        // 创建查询条件，按指定字段进行模糊搜索
        QueryWrapper<EmpHealth> userQueryWrapper = Wrappers.query();
        userQueryWrapper.like(searchkey,stext);
        // 返回符合条件的数据列表
        return mapper.selectList(userQueryWrapper);
    }
}
