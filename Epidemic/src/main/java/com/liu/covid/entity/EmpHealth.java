package com.liu.covid.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liu.covid.entity.Enum.GenderEnum;
import lombok.Data;

import java.util.Date;

/**
 * 员工健康信息实体类
 * 用于表示员工的健康数据，包括个人信息、健康状况、体温、风险评估等。
 */
@Data
public class EmpHealth {

    // 员工健康记录的唯一标识，主键自增长
    @TableId(type = IdType.AUTO)
    private Long id;

    // 员工姓名
    private String name;

    // 员工性别，使用 GenderEnum 枚举类型
    private GenderEnum sex;

    // 员工联系电话
    private Long phonenum;

    // 员工体温
    private float temp;

    // 健康风险评估
    private String risk;

    // 员工健康状态（如：健康、需要隔离等）
    private String health;

    // 健康内容的详细说明
    private String content;

    // 员工所属部门
    private String depart;

    // 记录的创建时间，插入时自动填充，格式为 "yyyy-MM-dd HH:mm:ss"
    @TableField(value = "createTime", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
