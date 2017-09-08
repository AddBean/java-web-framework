package com.base.http.resp;

/**
 * Created by Administrator on 2016/10/26.
 */
public class NewsResp {
    int code;
    String msg;
    DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean{
        int pay_inf_count;
        int pay_user_count;
        int pay_inf_new_count;
        int pay_user_new_count;

        public int getPay_inf_count() {
            return pay_inf_count;
        }

        public void setPay_inf_count(int pay_inf_count) {
            this.pay_inf_count = pay_inf_count;
        }

        public int getPay_user_count() {
            return pay_user_count;
        }

        public void setPay_user_count(int pay_user_count) {
            this.pay_user_count = pay_user_count;
        }

        public int getPay_inf_new_count() {
            return pay_inf_new_count;
        }

        public void setPay_inf_new_count(int pay_inf_new_count) {
            this.pay_inf_new_count = pay_inf_new_count;
        }

        public int getPay_user_new_count() {
            return pay_user_new_count;
        }

        public void setPay_user_new_count(int pay_user_new_count) {
            this.pay_user_new_count = pay_user_new_count;
        }
    }

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
}
