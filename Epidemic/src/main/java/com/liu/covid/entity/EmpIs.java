package com.liu.covid.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liu.covid.entity.Enum.GenderEnum;
import lombok.Data;

import java.util.Date;

/**
 * 员工隔离信息实体类
 * 用于表示员工的隔离状态，包括姓名、性别、隔离类型、体温、隔离起止日期等。
 */
@Data
public class EmpIs {

    // 员工隔离记录的唯一标识，主键自增长
    @TableId(type = IdType.AUTO)
    private Long id;

    // 员工姓名
    private String name;

    // 员工性别，使用 GenderEnum 枚举类型
    private GenderEnum sex;

    // 员工联系电话
    private Long phone;

    // 员工体温
    private float temp;

    // 隔离类型（例如：居家、集中等）
    private String type;

    // 隔离地点
    private String place;

    // 隔离开始日期，格式为 "yyyy-MM-dd"
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date begin;

    // 隔离结束日期，格式为 "yyyy-MM-dd"
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date end;

    // 是否离开隔离地点
    private String leaved;

    // 隔离期间的健康内容说明
    private String content;

    // 是否已到达隔离地点
    private String arrived;

    // 员工所属部门
    private String depart;
}
