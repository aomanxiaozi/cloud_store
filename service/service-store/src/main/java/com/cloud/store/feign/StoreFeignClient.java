package com.cloud.store.feign;


import com.cloud.store.feign.fallback.StoreFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-store",fallback = StoreFeignClientFallback.class)
public interface StoreFeignClient {
}
