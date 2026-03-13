    package com.cloud.user.feign;

    import com.cloud.user.feign.fallback.UserFeignClientFallback;
    import org.springframework.cloud.openfeign.FeignClient;

    @FeignClient(value = "service-user",fallback = UserFeignClientFallback.class)
    public interface UserFeignClient{
    }
