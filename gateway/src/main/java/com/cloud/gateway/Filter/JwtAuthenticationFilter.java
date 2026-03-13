package com.cloud.gateway.Filter;

import com.cloud.gateway.Util.JwtUtil;

import com.cloud.gateway.bean.SecurityConstants;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    // 定义一个白名单，这些路径不需要验证JWT
    private static final String[] WHITE_LIST = {"/api/user/login", "/api/user/register", "/public/**"};
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getURI().getPath();
        for (String whitePath : WHITE_LIST) {
            if (path.startsWith(whitePath.replace("/**", ""))) {
                // 白名单请求，直接放行
                return chain.filter(exchange);
            }
        }
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // 没有携带Token，返回401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 3. 提取 Token（去掉 "Bearer " 前缀）
        String token = authHeader.substring(7);

        // 4. 验证 Token
        if (!jwtUtil.validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        Claims claims = jwtUtil.parseToken(token);

        Long userId = claims.get("userId", Long.class);
        String username = claims.getSubject();
        Integer role = claims.get("role", Integer.class);
        ServerHttpRequest mutatedRequest = request.mutate()
                .header(SecurityConstants.USER_ID_HEADER, String.valueOf(userId))
                .header(SecurityConstants.USER_NAME_HEADER, username)
                .header(SecurityConstants.USER_ROLE_HEADER, String.valueOf(role))// 如果需要透传原Token
                .build();
        // 5. 继续处理请求...
        return chain.filter(exchange);
    }

    // 通过实现Ordered接口来指定过滤器的执行顺序，数字越小优先级越高
    @Override
    public int getOrder() {
        return -1;
    }
}
