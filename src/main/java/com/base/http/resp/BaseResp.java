package com.base.http.resp;

/**
 * Created by Administrator on 2016/10/25.
 */
public class BaseResp {
    int code;
    String msg;
    Object date;


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

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
