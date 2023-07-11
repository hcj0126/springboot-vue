package com.hcj.comm;

import com.alibaba.fastjson.JSONObject;

/**
 * JSONController
 *
 * @author hcj
 * @date 2023-06-16
 */
public abstract class JSONController extends BaseController {

    public JSONController() {
    }

    public String json(Integer code, String msg, Object data) {
        RepMsg resl = RepMsg.make(code, msg, data);
        return JSONObject.toJSONString(resl);
    }

    public String json(Integer code, String msg) {
        RepMsg resl = RepMsg.make(code, msg);
        return JSONObject.toJSONString(resl);
    }

    public String success() {
        return this.json(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
    }

    public String success(String msg) {
        return this.json(ResponseCode.SUCCESS.getCode(), msg);
    }

    public String success(Object data) {
        return this.json(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), data);
    }

    public String success(String msg, Object data) {
        return this.json(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public String empty() {
        return this.json(ResponseCode.EMPTY.getCode(), ResponseCode.EMPTY.getMsg());
    }

    public String empty(String msg) {
        return this.json(ResponseCode.EMPTY.getCode(), msg);
    }

    public String exit() {
        return this.json(ResponseCode.EXIT.getCode(), ResponseCode.EXIT.getMsg());
    }

    public String exit(String msg) {
        return this.json(ResponseCode.EXIT.getCode(), msg);
    }

    public String exit(Object data) {
        return this.json(ResponseCode.EXIT.getCode(), ResponseCode.EXIT.getMsg(), data);
    }

    public String exit(String msg, Object data) {
        return this.json(ResponseCode.EXIT.getCode(), msg, data);
    }

    public String refer() {
        return this.json(ResponseCode.REFER.getCode(), ResponseCode.REFER.getMsg());
    }

    public String refer(String msg) {
        return this.json(ResponseCode.REFER.getCode(), msg);
    }

    public String refter(Object data) {
        return this.json(ResponseCode.REFER.getCode(), ResponseCode.REFER.getMsg(), data);
    }

    public String refter(String msg, Object data) {
        return this.json(ResponseCode.REFER.getCode(), msg, data);
    }

    public String error() {
        return this.json(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg());
    }

    public String error(String msg) {
        return this.json(ResponseCode.ERROR.getCode(), msg);
    }
}
