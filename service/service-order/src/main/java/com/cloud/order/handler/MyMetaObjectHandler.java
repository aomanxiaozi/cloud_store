package com.cloud.order.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MyBatis-Plus 自动填充处理器
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        // 创建时间自动填充（如果字段为空）
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());

        // 更新时间自动填充
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        // 更新时间自动填充
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}