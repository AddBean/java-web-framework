package com.base.http.resp;

import com.base.entity.User;

/**
 * Created by Administrator on 2016/10/25.
 */
public class BaseResp {
    int code;
    String msg;
    Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
