package com.cloud.order.service.Impl;

import com.cloud.order.entity.InboundOrder;
import com.cloud.order.entity.OrderDetail;
import com.cloud.order.mapper.InboundOrderMapper;
import com.cloud.order.mapper.OrderDetailMapper;
import com.cloud.order.service.OrderService;
import com.cloud.order.until.OrderUtil;
import com.store.common.R;
import com.store.goods.bean.Goods;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    public InboundOrderMapper inboundOrderMapper;
    @Autowired
    public OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderUtil orderUtil;
    @Override
    @GlobalTransactional
    public R<String> InboundOrderCreate(InboundOrder inboundOrder) {
        InboundOrder order = new InboundOrder();
        String InboundOrderNo=orderUtil.generateOrderNo("in");
        int totalQuantity = inboundOrder.getGoodsList().stream()
                .mapToInt(Goods::getQuantity)  // 提取数量字段
                .sum();
        order.setOrderNo(InboundOrderNo);
        order.setWarehouseId(inboundOrder.getWarehouseId());
        order.setWarehouseName(inboundOrder.getWarehouseName());
        order.setSupplier(inboundOrder.getSupplier());
        order.setStatus(0);
        order.setRemark(inboundOrder.getRemark());
        order.setTotalQuantity(totalQuantity);
        order.setCreateBy(inboundOrder.getCreateBy());

        inboundOrderMapper.insert(order);

        List<OrderDetail> orderDetails=buildDetails(InboundOrderNo,1,inboundOrder.getGoodsList());
        orderDetailMapper.insert(orderDetails);
        return R.success("创建成功");
    }
    /**
     *
     */
    private List<OrderDetail> buildDetails(String orderNo,int type, List<Goods> goodsList) {
        List<OrderDetail> details = new ArrayList<>();
        for (Goods item : goodsList) {
            OrderDetail detail = new OrderDetail();
            detail.setOrderNo(orderNo);
            detail.setGoodsId(item.getId());
            detail.setOrderType(type);
            detail.setQuantity(item.getQuantity());
            detail.setLocation(item.getLocation());
            details.add(detail);
        }
        return details;
    }
}
