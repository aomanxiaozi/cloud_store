package com.cloud.order.controller;

import com.cloud.order.entity.InboundOrder;
import com.cloud.order.entity.OutboundOrder;
import com.cloud.order.service.OrderService;
import com.store.common.R;
import com.store.common.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    public OrderService orderService;
    /**
     * 入货订单创建
     */
    @PostMapping("/InboundOrderCreate")
    public R<String> InboundOrderCreate(@RequestBody InboundOrder inboundOrder, @RequestHeader(SecurityConstants.USER_NAME_HEADER) String username){
        inboundOrder.setCreateBy(username);
        return orderService.InboundOrderCreate(inboundOrder);
    }
    /**
     * 出货订单
     */
    @PostMapping("/OutboundOrderCreate")
    public R<String> OutboundOrderCreate(@RequestBody OutboundOrder outboundOrder, @RequestHeader(SecurityConstants.USER_NAME_HEADER) String username){
        outboundOrder.setCreateBy(username);
        return orderService.OutboundOrderCreate(outboundOrder);
    }
}
