package com.cloud.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("order_detail")
public class OrderDetail {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联单号
     */
    private String orderNo;

    /**
     * 类型：1-入库，2-出库
     */
    private Integer orderType;

    /**
     * 货物ID
     */
    private Long goodsId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 指定库位
     */
    private String location;

    /**
     * 创建时间
     */
    private Date createTime;

    // 非数据库字段 - 货物信息
    @TableField(exist = false)
    private String goodsName;

    @TableField(exist = false)
    private String goodsCode;

    @TableField(exist = false)
    private String unit;
}