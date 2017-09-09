package com.base.controller.api;

import com.base.controller.BaseController;
import com.base.entity.User;
import com.base.http.resp.BaseResp;
import com.base.http.resp.LoginResp;
import com.base.service.RoleService;
import com.base.utils.Const;
import com.base.utils.MD5Utils;
import com.base.utils.SecurityUtils;
import com.base.utils.TextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/6.
 */
@Controller
@RequestMapping("/api")
public class ApiLoginController extends BaseController {

    @Resource
    private RoleService mRoleServiceImpl;

    /**
     * 普通登陆接口
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/login.do")
    public LoginResp login(@RequestParam(value = "username") String username, String password, HttpServletRequest request) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(SecurityUtils.getInstance(Const.CONST_KEY).encrptString(password));
        user.setPasswordSalt(MD5Utils.String2md5(user.getPassword()));
        if (mUserServiceImpl.checkLogin(user)) {
            user = mUserServiceImpl.getUserInf(username);
            if (user == null)
                return new LoginResp(200, "用户不存在!");
            mUserServiceImpl.updateToken(user.getId());//更新token;
            LoginResp resp = new LoginResp(200, "登陆成功！");
            resp.setToken(mUserServiceImpl.updateToken(user.getId()));//更新token;
            return resp;
        } else {
            ;
            return new LoginResp(401, "帐号或密码错误:"+mUserServiceImpl.getUserInf(username).getPasswordSalt()+" --"+password+"-- "+user.getPassword()+" "+user.getPasswordSalt());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/user/register.do")
    public BaseResp register(HttpServletRequest request) {
        User user = new User();
        user.setUsername(TextUtils.getParameter(request, "username", ""));
        user.setPassword(SecurityUtils.getInstance(Const.CONST_KEY).encrptString(TextUtils.getParameter(request, "password", "")));
        user.setPasswordSalt(MD5Utils.String2md5(user.getPassword()));
        if (!TextUtils.isEmpty(user.getPassword()) && !TextUtils.isEmpty(user.getUsername())) {
            user.setRuleId(2);
            user.setStatus(Const.USER_STATUS_ACTIVE);
            Date data = new Date();
            user.setUpdateTime(data);
            user.setAddTime(data);
            if (mUserServiceImpl.addUser(user)) {
                BaseResp baseResp = new BaseResp();
                baseResp.setMsg("注册成功!");
                baseResp.setCode(200);
                baseResp.setData(user);
                return baseResp;
            } else {
                BaseResp baseResp = new BaseResp();
                baseResp.setMsg("注册失败,可能该用户名已经存在");
                baseResp.setCode(400);
                return baseResp;
            }
        }
        BaseResp baseResp = new BaseResp();
        baseResp.setMsg("注册失败!");
        baseResp.setCode(400);
        return baseResp;

    }
}
