package com.liu.covid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.covid.entity.MaterialManage;
import com.liu.covid.mapper.MaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资管理控制器
 */
@RestController
@RequestMapping("/Material")
public class MaterialController {

    @Autowired
    private MaterialMapper mapper; // 物资管理的 Mapper

    /**
     * 分页查询物资信息
     * @param page 当前页码
     * @param size 每页显示条数
     * @return 分页后的物资信息列表
     */
    @GetMapping("/findAll/{page}/{size}")
    public Page<MaterialManage> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Page<MaterialManage> page1 = new Page<>(page, size);
        Page<MaterialManage> result = mapper.selectPage(page1, null);
        return result;
    }

    /**
     * 保存新的物资信息
     * @param material 物资管理对象
     * @return 操作结果：成功返回 "success"，失败返回 "error"
     */
    @PostMapping("/save")
    public String save(@RequestBody MaterialManage material) {
        int result = mapper.insert(material);
        if (result == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 根据 ID 查询物资信息
     * @param id 物资 ID
     * @return 对应的物资管理对象
     */
    @GetMapping("/findById/{id}")
    public MaterialManage findById(@PathVariable("id") Integer id) {
        return mapper.selectById(id);
    }

    /**
     * 更新物资信息
     * @param material 物资管理对象
     * @return 操作结果：成功返回 "success"，失败返回 "error"
     */
    @PutMapping("/update")
    public String update(@RequestBody MaterialManage material) {
        int result = mapper.updateById(material);
        if (result == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 根据物资 ID 删除物资信息
     * @param id 物资 ID
     */
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        mapper.deleteById(id + "L");
    }

    /**
     * 根据字段名和关键字模糊查询物资信息
     * @param searchkey 字段名
     * @param stext 查询关键字
     * @return 符合条件的物资信息列表
     */
    @GetMapping("/search/{searchkey}/{stext}")
    public List<MaterialManage> search(@PathVariable("searchkey") String searchkey, @PathVariable("stext") String stext) {
        QueryWrapper<MaterialManage> userQueryWrapper = Wrappers.query();
        userQueryWrapper.like(searchkey, stext); // 模糊查询
        return mapper.selectList(userQueryWrapper);
    }
}
