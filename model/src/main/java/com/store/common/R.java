package com.store.common;

import lombok.Data;

@Data
public class R<T> {
    private Boolean flag;
    private T data;
    private String msg;
    private Integer code;
    public R(){}
    public R(Boolean flag, Integer code, String msg, T data) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public R(boolean flag) {
        this.flag = flag;
    }

    public R(Boolean flag, Integer code, String msg) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
    }

    public static <T> R<T> success(String msg){return new R<T>(true, 200,msg);}
    public static <T> R<T> success(T data) {
        return new R<T>(true, 200, "操作成功", data);
    }

    // 快速创建成功响应（自定义描述信息）
    public static <T> R<T> success(String msg, T data) {
        return new R<T>(true, 200, msg, data);
    }

    // 快速创建失败响应
    public static <T> R<T> error(String msg) {
        return new R<T>(false, 500, msg, null);
    }

    // 快速创建失败响应（自定义状态码）
    public static <T> R<T> error(Integer code, String msg) {
        return new R<T>(false, code, msg, null);
    }
}
