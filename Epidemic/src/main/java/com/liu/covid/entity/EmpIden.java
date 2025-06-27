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
 * 员工身份信息实体类
 * 用于表示员工的身份信息，包括姓名、性别、身份证号码、入职时间等。
 */
@Data
public class EmpIden {

    // 员工身份记录的唯一标识，主键自增长
    @TableId(type = IdType.AUTO)
    private Long id;

    // 员工姓名
    private String name;

    // 员工状态（例如：在职、离职等）
    private String status;

    // 员工性别，使用 GenderEnum 枚举类型
    private GenderEnum sex;

    // 员工身份证号码
    private Long idcard;

    // 员工身份证发证日期，格式为 "yyyy-MM-dd"
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date idate;

    // 员工出生地
    private String place;

    // 员工所属部门
    private String depart;

    // 员工联系电话
    private Long phonenum;

    // 员工注册时间，插入时自动填充，格式为 "yyyy-MM-dd HH:mm:ss"
    @TableField(value = "register", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date register;
}
