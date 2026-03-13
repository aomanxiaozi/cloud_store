package com.cloud.user.feign.fallback;

import com.cloud.user.feign.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient {
}
