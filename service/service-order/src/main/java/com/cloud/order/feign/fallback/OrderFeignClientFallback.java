package com.cloud.order.feign.fallback;

import com.cloud.order.feign.OrderFeignClient;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignClientFallback implements OrderFeignClient {

}
