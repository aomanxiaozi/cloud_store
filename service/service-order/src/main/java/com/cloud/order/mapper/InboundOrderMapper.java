package com.cloud.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.order.entity.InboundOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InboundOrderMapper extends BaseMapper<InboundOrder> {
}
