package com.hcj.comm;

/**
 * ResponseCode
 *
 * @author hcj
 * @date 2023-06-16
 */
public enum ResponseCode {
    SUCCESS(0, "处理成功"),
    ERROR(1, "服务异常"),
    EMPTY(2, "未找到相关内容"),
    EXIT(3, "信息已存在"),
    REFER(4, "存在相关内容");

    private String msg;
    private Integer code;

    private ResponseCode(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
