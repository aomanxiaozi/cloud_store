package com.cloud.user.Util;

import com.cloud.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret; // 从配置文件读取密钥，必须与认证服务一致

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    // 从Token中解析所有Claims（包含用户信息）

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()  // 注意这里是 parserBuilder()
                .setSigningKey(getSigningKey())  // 使用 setSigningKey
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("username", user.getUsername());
        claims.put("role",user.getRole());
        return "Bearer "+Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1小时
                .signWith(getSigningKey())
                .compact();
    }
    // 验证Token是否有效（签名和有效期）
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false;  // 过期
        } catch (JwtException | IllegalArgumentException e) {
            return false;  // 无效
        }
    }

    // 可以从Claims中提取特定信息，例如用户名
    public String getUsernameFromToken(String token) {
        return parseToken(token).getSubject();
    }
}
