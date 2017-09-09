package com.base.controller.api;

import com.base.controller.BaseController;
import com.base.entity.User;
import com.base.entity.UserDetail;
import com.base.filter.AuthFilterHelper;
import com.base.http.resp.UserDetailResp;
import com.base.service.UserDetailService;
import com.base.utils.Const;
import com.base.utils.MD5Utils;
import com.base.utils.SecurityUtils;
import com.base.utils.TextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/14.
 */
@Controller
@RequestMapping("/api")
public class ApiUserController extends BaseController {
    @Resource
    protected UserDetailService mUserDetailServiceImpl;

    @ResponseBody
    @RequestMapping(value = "/user/getUserInf.do", method = RequestMethod.GET)
    public UserDetailResp getUserInf(HttpServletRequest request, HttpServletResponse response) {
        if (!AuthFilterHelper.isLogin(mUserServiceImpl, request, response)) {
            return null;
        }
        User user;
        if (!TextUtils.hasParameter(request, "id")) {
            String token = request.getHeader("token");
            user = mUserServiceImpl.getUserByToken(token);
        }else{
            user = mUserServiceImpl.getUserInfById(TextUtils.getParameter(request,"id",1));
        }
        UserDetail userDetail = mUserDetailServiceImpl.getUserDetailByUserId(user.getId());
        return new UserDetailResp(user, userDetail);
    }


    @ResponseBody
    @RequestMapping(value = "/user/editUser.do")
    public UserDetailResp editUser(HttpServletRequest request, HttpServletResponse response) {
        if (!AuthFilterHelper.isLogin(mUserServiceImpl, request, response)) {
            return null;
        }
        User user;
        if (TextUtils.hasParameter(request, "id")) {
            user = mUserServiceImpl.getUserInfById(TextUtils.getParameter(request, "id", 1));
        } else {
            String token = request.getHeader("token");
             user = mUserServiceImpl.getUserByToken(token);
        }
        UserDetail userDetail = mUserDetailServiceImpl.getUserDetailByUserId(user.getId());
        if (TextUtils.hasParameter(request, "password")) {
            user.setPassword(SecurityUtils.getInstance(Const.CONST_KEY).encrptString(TextUtils.getParameter(request, "password", "")));
            user.setPasswordSalt(MD5Utils.String2md5(user.getPassword()));
        }
        if (TextUtils.hasParameter(request, "status"))
            user.setStatus(TextUtils.getParameter(request, "status", 1));
        if (TextUtils.hasParameter(request, "nickname"))
            user.setNickname(TextUtils.getParameter(request, "nickname", ""));

        user.setUpdateTime(new Date());
        mUserServiceImpl.updateUser(user);

        if (TextUtils.hasParameter(request, "age"))
            userDetail.setAge(TextUtils.getParameter(request, "age", "1"));
        if (TextUtils.hasParameter(request, "phone"))
            userDetail.setPhone(TextUtils.getParameter(request, "phone", ""));
        if (TextUtils.hasParameter(request, "address"))
            userDetail.setAddress(TextUtils.getParameter(request, "address", ""));
        if (TextUtils.hasParameter(request, "avatar"))
            userDetail.setAvatar(TextUtils.getParameter(request, "avatar", ""));
        if (TextUtils.hasParameter(request, "sex"))
            userDetail.setSex(TextUtils.getParameter(request, "sex", ""));
        if (TextUtils.hasParameter(request, "ext"))
            userDetail.setExt(TextUtils.getParameter(request, "ext", ""));
        mUserDetailServiceImpl.updateUserDetail(userDetail);
        UserDetailResp resp = new UserDetailResp(user, userDetail);
        resp.setCode(200);
        resp.setMsg("获取成功");
        return resp;
    }

}
