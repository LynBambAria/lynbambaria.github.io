package com.liu.covid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.covid.entity.EmpIden;
import com.liu.covid.mapper.EmpIdenMapper;
import com.liu.covid.service.ChartService;
import com.liu.covid.vo.LineVO;
import com.liu.covid.vo.PieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 员工身份信息管理控制器
 */
@RestController
@RequestMapping("/empiden")
public class EmpIdenController {

    @Autowired
    private ChartService chartService; // 图表服务

    @Autowired
    private EmpIdenMapper mapper; // 员工身份信息的 Mapper

    /**
     * 分页查询员工身份信息
     * @param page 当前页码
     * @param size 每页显示条数
     * @return 分页后的员工身份信息列表
     */
    @GetMapping("/findAll/{page}/{size}")
    public Page<EmpIden> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Page<EmpIden> page1 = new Page<>(page, size);
        Page<EmpIden> result = mapper.selectPage(page1, null);
        return result;
    }

    /**
     * 保存新的员工身份信息
     * @param empIden 员工身份信息对象
     * @return 操作结果：成功返回 "success"，失败返回 "error"
     */
    @PostMapping("/save")
    public String save(@RequestBody EmpIden empIden) {
        int result = mapper.insert(empIden);
        if (result == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 获取折线图数据
     * @return 折线图数据对象
     */
    @GetMapping("/LineVO")
    public LineVO getLineVO() {
        return this.chartService.lineVOList();
    }

    /**
     * 获取饼图数据
     * @return 饼图数据列表
     */
    @GetMapping("/PieVO")
    public List<PieVo> getPieVO() {
        return this.chartService.pieVOMap();
    }

    /**
     * 根据员工 ID 查询员工身份信息
     * @param id 员工 ID
     * @return 对应的员工身份信息
     */
    @GetMapping("/findById/{id}")
    public EmpIden findById(@PathVariable("id") Integer id) {
        return mapper.selectById(id);
    }

    /**
     * 更新员工身份信息
     * @param empIden 员工身份信息对象
     * @return 操作结果：成功返回 "success"，失败返回 "error"
     */
    @PutMapping("/update")
    public String update(@RequestBody EmpIden empIden) {
        int result = mapper.updateById(empIden);
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
    public List<EmpIden> search(@PathVariable("searchkey") String searchkey, @PathVariable("stext") String stext) {
        QueryWrapper<EmpIden> userQueryWrapper = Wrappers.query();
        userQueryWrapper.like(searchkey, stext);
        return mapper.selectList(userQueryWrapper);
    }
}
