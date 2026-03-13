package com.cloud.order.handler;

import com.store.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public R<String> handleBusinessException(RuntimeException e) {
        // 记录业务异常日志（warn级别，不需要堆栈）
        log.error("系统异常详情", e);
        log.warn("业务异常: {}", e.getMessage());
        return R.error(e.getMessage());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(org.springframework.validation.BindException.class)
    public R<String> handleBindException(org.springframework.validation.BindException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.warn("参数校验异常: {}", message);
        return R.error(message);
    }

    /**
     * 数据库异常
     */
    @ExceptionHandler(java.sql.SQLException.class)
    public R<String> handleSQLException(java.sql.SQLException e) {
        log.error("数据库异常: {}", e.getMessage(), e);
        return R.error("数据库操作失败");
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public R<String> handleNullPointerException(NullPointerException e) {
        log.error("空指针异常", e);
        return R.error("系统内部错误");
    }

    /**
     * 其他未知异常
     */
    @ExceptionHandler(Exception.class)
    public R<String> handleException(Exception e) {
        // 记录完整异常信息
        log.error("系统异常", e);

        // 可选：将异常堆栈转为字符串
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stackTrace = sw.toString();
        log.error("异常堆栈: {}", stackTrace);

        return R.error("系统繁忙，请稍后重试");
    }
}
