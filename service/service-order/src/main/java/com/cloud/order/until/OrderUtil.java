package com.cloud.order.until;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class OrderUtil {

    private final StringRedisTemplate redisTemplate;

    public String generateOrderNo(String prefix){
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // 2. Redis key：order:seq:20260313:IN/out
        String key = "order:seq:" + date+":" + prefix;
        Long seq = redisTemplate.opsForValue().increment(key);

        // 4. 设置过期时间（当天有效）
        //    计算到午夜还剩下多少秒
        long secondsUntilMidnight = getSecondsUntilMidnight();
        redisTemplate.expire(key, secondsUntilMidnight, TimeUnit.SECONDS);

        // 5. 格式化为4位数字，不足补零
        String seqStr = String.format("%04d", seq);

        return prefix + date + seqStr;
    }
    /**
     * 计算到午夜0点的秒数
     */
    private long getSecondsUntilMidnight() {
        Date now = new Date();
        Date midnight = getMidnightDate(now);
        return (midnight.getTime() - now.getTime()) / 1000;
    }

    /**
     * 获取当天的午夜时间
     */
    private Date getMidnightDate(Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        cal.set(java.util.Calendar.HOUR_OF_DAY, 24);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);
        cal.set(java.util.Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
