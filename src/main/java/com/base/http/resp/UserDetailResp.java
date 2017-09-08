package com.base.http.resp;

import com.base.entity.User;
import com.base.entity.UserDetail;

/**
 * Created by Administrator on 2017/9/7.
 */
public class UserDetailResp extends BaseResp {
    private User user;
    private UserDetail userDetail;

    public UserDetailResp(User user, UserDetail userDetail) {
        this.user = user;
        this.userDetail = userDetail;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
