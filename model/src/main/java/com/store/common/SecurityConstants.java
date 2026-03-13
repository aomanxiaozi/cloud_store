package com.store.common;

public class SecurityConstants {

    /**
     * Token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 认证头
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * 用户ID请求头（网关传递给下游）
     */
    public static final String USER_ID_HEADER = "X-User-Id";

    /**
     * 用户名请求头
     */
    public static final String USER_NAME_HEADER = "X-User-Name";

    /**
     * 用户角色请求头
     */
    public static final String USER_ROLE_HEADER = "X-User-Role";

    /**
     * 原始Token头（如果需要透传）
     */
    public static final String TOKEN_HEADER = "X-Token";

    /**
     * 请求ID头（链路追踪）
     */
    public static final String REQUEST_ID_HEADER = "X-Request-Id";

    /**
     * JWT密钥（实际应从配置中心读取）
     */
    public static final String JWT_SECRET = "your-256-bit-secret-key-for-wms-system-2024";

    /**
     * Token过期时间（毫秒）- 24小时
     */
    public static final long TOKEN_EXPIRATION = 86400000L;

    /**
     * 角色常量
     */
    public static final int ROLE_ADMIN = 1;      // 管理员
    public static final int ROLE_WAREHOUSE = 2;  // 仓管员
    public static final int ROLE_OPERATOR = 3;   // 操作员

    /**
     * 状态常量
     */
    public static final int STATUS_NORMAL = 1;    // 正常
    public static final int STATUS_DISABLED = 0;  // 禁用
}
