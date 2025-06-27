package com.liu.covid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.covid.entity.EmpIs;
import com.liu.covid.mapper.EmpIsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 员工身份信息控制器
 */
@RestController
@RequestMapping("/empis")
public class EmpIsController {

    @Autowired
    private EmpIsMapper mapper; // 员工身份信息的 Mapper

    /**
     * 分页查询员工身份信息
     * @param page 当前页码
     * @param size 每页显示条数
     * @return 分页后的员工身份信息列表
     */
    @GetMapping("/findAll/{page}/{size}")
    public Page<EmpIs> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Page<EmpIs> page1 = new Page<>(page, size);
        Page<EmpIs> result = mapper.selectPage(page1, null);
        return result;
    }

    /**
     * 保存新的员工身份信息
     * @param empis 员工身份信息对象
     * @return 操作结果：成功返回 "success"，失败返回 "error"
     */
    @PostMapping("/save")
    public String save(@RequestBody EmpIs empis) {
        // 设置结束日期为开始日期后14天
        Format f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(empis.getBegin());
        c.add(Calendar.DAY_OF_MONTH, 14); // 增加14天
        Date end = c.getTime();
        empis.setEnd(end);

        int result = mapper.insert(empis);
        if (result == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 根据 ID 查询员工身份信息
     * @param id 员工 ID
     * @return 对应的员工身份信息对象
     */
    @GetMapping("/findById/{id}")
    public EmpIs findById(@PathVariable("id") Integer id) {
        return mapper.selectById(id);
    }

    /**
     * 更新员工身份信息
     * @param empis 员工身份信息对象
     * @return 操作结果：成功返回 "success"，失败返回 "error"
     */
    @PutMapping("/update")
    public String update(@RequestBody EmpIs empis) {
        // 设置结束日期为开始日期后14天
        Format f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(empis.getBegin());
        c.add(Calendar.DAY_OF_MONTH, 14); // 增加14天
        Date end = c.getTime();
        empis.setEnd(end);

        int result = mapper.updateById(empis);
        if (result == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 根据员工 ID 删除员工身份信息
     * @param id 员工 ID
     */
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        mapper.deleteById(id + "L");
    }

    /**
     * 根据字段名和关键字模糊查询员工身份信息
     * @param searchkey 字段名
     * @param stext 查询关键字
     * @return 符合条件的员工身份信息列表
     */
    @GetMapping("/search/{searchkey}/{stext}")
    public List<EmpIs> search(@PathVariable("searchkey") String searchkey, @PathVariable("stext") String stext) {
        QueryWrapper<EmpIs> userQueryWrapper = Wrappers.query();
        userQueryWrapper.like(searchkey, stext); // 模糊查询
        return mapper.selectList(userQueryWrapper);
    }
}
