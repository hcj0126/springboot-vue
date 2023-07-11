package com.hcj.comm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RepMsg
 *
 * @author hcj
 * @date 2023-06-16
 */
public class RepMsg {
    private Integer code;
    private String msg;
    private Object data;

    public RepMsg() {
    }

    public RepMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RepMsg(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RepMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static RepMsg make(int code, String msg) {
        return new RepMsg(code, msg);
    }

    public static RepMsg make(int code, String msg, Object data) {
        return new RepMsg(code, msg, data);
    }
}
