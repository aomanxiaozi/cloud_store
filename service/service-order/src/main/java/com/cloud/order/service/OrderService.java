package com.cloud.order.service;

import com.cloud.order.entity.InboundOrder;
import com.store.common.R;

public interface OrderService {
    R<String> InboundOrderCreate(InboundOrder inboundOrder);
}
