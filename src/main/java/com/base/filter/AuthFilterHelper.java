package com.base.filter;

import com.base.entity.User;
import com.base.http.resp.BaseResp;
import com.base.service.UserService;
import com.base.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/9/7.
 */
public class AuthFilterHelper {

    public static boolean isLogin(UserService mUserServiceImpl, HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token) && mUserServiceImpl.getUserByToken(token) != null) return true;
        BaseResp baseResp = new BaseResp();
        baseResp.setCode(401);
        baseResp.setMsg("未登录");
        HttpUtils.responseOutWithJson(response, baseResp);
        return false;
    }

    public static User getCurrentUser(UserService mUserServiceImpl, HttpServletRequest request) {
        String token = request.getHeader("token");
        return mUserServiceImpl.getUserByToken(token) ;
    }
}
