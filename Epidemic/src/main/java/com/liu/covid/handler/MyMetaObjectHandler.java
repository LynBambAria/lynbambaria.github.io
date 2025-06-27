package com.liu.covid.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义 MyMetaObjectHandler 实现自动填充功能
 * 用于在插入或更新数据时自动填充指定字段的值，如创建时间、更新时间等。
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时自动填充字段
     * 在插入数据时，自动填充 createTime、register 和 updateTime 字段。
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 设置 createTime 字段为当前时间
        this.setFieldValByName("createTime", new Date(), metaObject);
        // 设置 register 字段为当前时间
        this.setFieldValByName("register", new Date(), metaObject);
        // 设置 updateTime 字段为当前时间
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    /**
     * 更新时自动填充字段
     * 在更新数据时，自动填充 updateTime 字段为当前时间。
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 设置 updateTime 字段为当前时间
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
