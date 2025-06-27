package com.liu.covid.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liu.covid.entity.Enum.ImpEnum;
import lombok.Data;

import java.util.Date;

/**
 * 物资管理实体类
 * 用于表示物资的管理信息，包括物资的名称、数量、类型、负责人等。
 */
@Data
@TableName(value = "material_manage")
public class MaterialManage {

    // 物资管理记录的唯一标识，主键自增长
    @TableId(type = IdType.AUTO)
    private Long id;

    // 物资名称
    private String name;

    // 物资数量
    private int count;

    // 物资类型
    private String type;

    // 物资是否重要，使用 ImpEnum 枚举类型（例如：是、否）
    @TableField(value = "isImp")
    private ImpEnum isImp;

    // 物资负责人
    private String charge;

    // 物资负责人联系方式
    private Long cnum;

    // 物资管理记录的更新时间，插入或更新时自动填充，格式为 "yyyy-MM-dd HH:mm:ss"
    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
