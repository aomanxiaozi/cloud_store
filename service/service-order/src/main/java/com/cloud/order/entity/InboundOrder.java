package com.cloud.order.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.store.goods.bean.Goods;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("inbound_order")
public class InboundOrder {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 入库单号
     */
    private String orderNo;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 状态：0-待入库，1-已入库，2-已取消
     */
    private Integer status;

    /**
     * 总数量
     */
    private Integer totalQuantity;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // 非数据库字段 - 用于关联查询
    @TableField(exist = false)
    private List<OrderDetail> details;
    //非数据库字段：用于接收前端传的明细
    @TableField(exist = false)
    private List<Goods> goodsList;
    // 非数据库字段 - 仓库名称
    @TableField(exist = false)
    private String warehouseName;

    // 状态的中文描述
    public String getStatusDesc() {
        return switch (status) {
            case 0 -> "待入库";
            case 1 -> "已入库";
            case 2 -> "已取消";
            default -> "未知";
        };
    }
}
