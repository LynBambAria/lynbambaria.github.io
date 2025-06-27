package com.liu.covid.entity.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 是否重要枚举类型
 * 用于表示是否重要的状态，包含“是”和“否”两种选项。
 */
public enum ImpEnum {

    是(1, "是"), // 表示重要，code为1
    否(0, "否"); // 表示不重要，code为0

    // 枚举类的构造函数，传入code和isImp值
    ImpEnum(Integer code, String isImp) {
        this.code = code;
        this.isImp = isImp;
    }

    @EnumValue // 标记该字段为数据库存储的值
    private Integer code; // 是否重要的代码：1为是，0为否
    private String isImp; // 是否重要的名称：是或否
}
