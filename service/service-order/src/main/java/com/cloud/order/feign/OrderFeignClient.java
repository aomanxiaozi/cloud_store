package com.cloud.order.feign;

import com.cloud.order.feign.fallback.OrderFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-order",fallback = OrderFeignClientFallback.class)
public interface OrderFeignClient {
}
