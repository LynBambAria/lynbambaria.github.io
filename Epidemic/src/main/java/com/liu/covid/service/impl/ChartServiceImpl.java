package com.liu.covid.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.covid.entity.EmpIden;
import com.liu.covid.entity.EmpIs;
import com.liu.covid.entity.MaterialManage;
import com.liu.covid.mapper.EmpIdenMapper;
import com.liu.covid.mapper.EmpIsMapper;
import com.liu.covid.mapper.MaterialMapper;
import com.liu.covid.service.ChartService;
import com.liu.covid.vo.LineVO;
import com.liu.covid.vo.PieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ChartServiceImpl 是 ChartService 接口的实现类，用于生成图表数据。
 * 包含了处理折线图数据和饼图数据的方法。
 */
@Service
public class ChartServiceImpl extends ServiceImpl<EmpIdenMapper, EmpIden> implements ChartService {

    @Autowired
    private EmpIdenMapper empIdenMapper;  // 自动注入 EmpIdenMapper，用于操作身份证信息表
    @Autowired
    private EmpIsMapper empIsMapper;  // 自动注入 EmpIsMapper，用于操作隔离信息表
    @Autowired
    private MaterialMapper materialMapper;  // 自动注入 MaterialMapper，用于操作物资管理表

    /**
     * lineVOList 生成折线图的数据，显示过去 7 个月内各类疾病状态的变化。
     * @return LineVO 包含折线图所需的月份和各状态数量
     */
    @Override
    public LineVO lineVOList() {
        LineVO lineVO = new LineVO();  // 创建 LineVO 对象，用于存储折线图数据
        Date date = new Date();  // 当前时间
        Calendar cal = Calendar.getInstance();  // 获取当前时间的 Calendar 对象
        List<String> month = new ArrayList<>();  // 用于存储过去 7 个月的月份
        List<Integer> list = new ArrayList<>();  // 用于存储隔离人数的数量
        Map<String, List> all = new HashMap<>();  // 存储每个疾病状态的数量
        String type[] = {"确诊", "疑似", "治愈", "死亡"};  // 疾病状态类型数组

        // 获取过去 7 个月的月份
        for (int i = 0; i < 7; i++) {
            cal.setTime(date);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - i);  // 设置当前月份减去 i 个月
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");  // 格式化日期为 "yyyy-MM" 格式
            String mon = ft.format(cal.getTime());  // 获取当前月份
            month.add(mon);  // 将月份添加到 month 列表中
        }
        Collections.reverse(month);  // 翻转月份列表，确保月份顺序从过去到现在
        lineVO.setMonth(month);  // 设置 LineVO 中的月份

        // 获取每个疾病状态类型在过去 7 个月内的数量
        for (String t : type) {
            List<Integer> cot = new ArrayList<>();
            int j = 0;
            while (j < 7) {
                QueryWrapper<EmpIden> userQueryWrapper = Wrappers.query();  // 创建查询条件
                userQueryWrapper.like("status", t).likeRight("idate", month.get(j++));  // 根据状态和月份查询
                Integer count = empIdenMapper.selectCount(userQueryWrapper);  // 获取符合条件的记录数量
                cot.add(count);  // 将数量添加到 cot 列表中
                userQueryWrapper.clear();  // 清除查询条件
            }
            all.put(t, cot);  // 将疾病状态和数量添加到 all 映射中
        }

        // 获取过去 7 个月内的隔离人数
        int j = 0;
        while (j < 7) {
            QueryWrapper<EmpIs> userQueryWrapper = Wrappers.query();  // 创建查询条件
            userQueryWrapper.likeRight("begin", month.get(j++));  // 根据月份查询
            Integer count = empIsMapper.selectCount(userQueryWrapper);  // 获取隔离人数
            list.add(count);  // 将隔离人数添加到 list 列表中
        }
        all.put("隔离", list);  // 将隔离人数添加到 all 映射中
        lineVO.setStatus(all);  // 设置 LineVO 中的疾病状态数量映射
        return lineVO;  // 返回 LineVO 对象
    }

    /**
     * pieVOMap 生成饼图的数据，显示重要物资的数量。
     * @return List<PieVo> 包含物资名称和数量的列表
     */
    @Override
    public List<PieVo> pieVOMap() {
        List<PieVo> pielist = new ArrayList<>();  // 用于存储饼图数据的列表
        QueryWrapper queryWrapper = new QueryWrapper();  // 创建查询条件
        queryWrapper.eq("isImp", "1");  // 查询 isImp 字段为 1 的记录，表示重要物资
        List<MaterialManage> list = materialMapper.selectList(queryWrapper);  // 获取符合条件的物资记录

        // 遍历所有重要物资，创建 PieVo 对象存储物资名称和数量
        for (MaterialManage mat : list) {
            PieVo pieVo = new PieVo();
            pieVo.setName(mat.getName());  // 设置物资名称
            pieVo.setValue(mat.getCount());  // 设置物资数量
            pielist.add(pieVo);  // 将 PieVo 对象添加到 pielist 列表中
        }
        return pielist;  // 返回饼图数据列表
    }

}
