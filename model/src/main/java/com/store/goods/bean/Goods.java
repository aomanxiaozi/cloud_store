package com.store.goods.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
@Data
public class Goods {
    private Long id;
    private String goodsCode;
    private String goodsName;
    private String specification;
    private String unit;
    private Date createTime;
    @TableField(exist = false)
    private Integer quantity;
    @TableField(exist = false)
    private String location;
}
