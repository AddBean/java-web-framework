package com.base.http.resp;

/**
 * Created by Administrator on 2016/10/18.
 */
public class LoginResp extends BaseResp {
    private String token;
    public LoginResp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
