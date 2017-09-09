package com.base.controller.web;

import com.base.controller.BaseController;
import com.base.entity.User;
import com.base.http.resp.LoginResp;
import com.base.service.RoleService;
import com.base.utils.Const;
import com.base.utils.MD5Utils;
import com.base.utils.SecurityUtils;
import com.base.utils.VerifyCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2016/9/6.
 */
@Controller
@RequestMapping("/web")
public class LoginController extends BaseController {

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
    public LoginResp login(@RequestParam(value = "username") String username, String password, String verifycode, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        User user = new User();
        user.setUsername(username);
        user.setPassword(SecurityUtils.getInstance(Const.CONST_KEY).encrptString(password));
        user.setPasswordSalt(MD5Utils.String2md5(user.getPassword()));
        String validateCode = (String) session.getAttribute("validateCode");
        if (validateCode.equalsIgnoreCase(verifycode) || StringUtils.isEmpty(validateCode)) {
            if (mUserServiceImpl.checkLogin(user)) {
                user = mUserServiceImpl.getUserInf(username);
                if (user == null)
                    return new LoginResp(401, "用户不存在!");
                if (mRoleServiceImpl.getRole(user.getRuleId()).getRoleType() != Const.ROLE_ADMIN) {
                    return new LoginResp(401, "该用户权限不够!");
                }
                mUserServiceImpl.updateToken(user.getId());//更新token;
                session.setAttribute("token", mUserServiceImpl.updateToken(user.getId()));
                return new LoginResp(200, "登陆成功！");
            } else {
                return new LoginResp(401, "帐号或密码错误");
            }
        } else {
            return new LoginResp(401, "验证码错误");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/user/logout.do")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("token", null);
        return "true";
    }

    @ResponseBody
    @RequestMapping(value = "/user/verify.do", method = RequestMethod.GET)
    public boolean getVerifyResult(String code, HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String validateCode = (String) session.getAttribute("validateCode");
        if (validateCode == null || code == null) return false;
        if (validateCode.equalsIgnoreCase(code)) return true;
        return false;
    }

    @RequestMapping(value = "/user/getverify.do", method = RequestMethod.GET)
    public void getVerifyImage(String time, HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        VerifyCodeUtils.VerifyCode code = VerifyCodeUtils.getVerifyCodeImage(200, 64, 64, 4);
        session.setAttribute("validateCode", code.code);
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        try {
            ServletOutputStream sos = resp.getOutputStream();
            ImageIO.write(code.buffImg, "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
