package com.liu.covid.entity.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 性别枚举类型
 * 用于表示性别信息，包含男和女两种类型。
 */
public enum GenderEnum {

    男(1, "男"), // 男性，code为1
    女(0, "女"); // 女性，code为0

    // 枚举类的构造函数，传入code和gender值
    GenderEnum(Integer code, String gender) {
        this.code = code;
        this.gender = gender;
    }

    @EnumValue // 标记该字段为数据库存储的值
    private Integer code; // 性别代码：1为男性，0为女性
    private String gender; // 性别名称：男或女
}
