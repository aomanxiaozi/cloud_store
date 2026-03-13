package com.cloud.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.store.goods.bean.Goods;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@TableName("outbound_order")
public class OutboundOrder {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 出库单号
     */
    private String orderNo;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 客户
     */
    private String customer;

    /**
     * 状态：0-待出库，1-已出库，2-已取消
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

    @TableField(exist = false)
    private List<Goods> goodsList;

    @TableField(exist = false)
    private String warehouseName;

    public String getStatusDesc() {
        return switch (status) {
            case 0 -> "待出库";
            case 1 -> "已出库";
            case 2 -> "已取消";
            default -> "未知";
        };
    }
}
