package com.base.controller.api;

import com.base.controller.BaseController;
import com.base.entity.Record;
import com.base.entity.User;
import com.base.filter.AuthFilterHelper;
import com.base.http.resp.BaseResp;
import com.base.service.EventService;
import com.base.utils.TextUtils;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/9.
 */
@Controller
@RequestMapping("/api")
public class ApiEventController extends BaseController {
    @Resource
    protected EventService mEventService;

    @ResponseBody
    @RequestMapping(value = "/event/addRecord.do")
    public BaseResp addRecord(HttpServletRequest request, HttpServletResponse response) {
        Record record = new Record();
        record.setName(TextUtils.getParameter(request, "name", ""));
        record.setDetail(TextUtils.getParameter(request, "detail", ""));
        record.setEx(TextUtils.getParameter(request, "ex", ""));
        record.setIp(TextUtils.getParameter(request, "ip", ""));
        record.setEx1(TextUtils.getParameter(request, "ex1", ""));
        record.setUuid(TextUtils.getParameter(request, "uuid", ""));
        record.setSysVer(TextUtils.getParameter(request, "sysVer", ""));
        record.setAddTime(new Date());
        User user = AuthFilterHelper.getCurrentUser(mUserServiceImpl, request);
        if (user != null)
            record.setUserId(user.getId());
        if (TextUtils.hasParameter(request, "userId")) {
            Integer userId = TextUtils.getParameter(request, "userId", 0);
            record.setUserId(userId);
        }
        System.out.print(new Gson().toJson(record) + "\n");
        BaseResp baseResp = new BaseResp();
        if (!TextUtils.isEmpty(record.getName())&&mEventService.addRecord(record) == 1) {
            mEventService.addEventByRecord(record);
            baseResp.setCode(200);
            baseResp.setMsg("添加成功");
        } else {
            baseResp.setCode(500);
            baseResp.setMsg("添加出错");
        }
        return baseResp;
    }

    @ResponseBody
    @RequestMapping(value = "/event/delRecord.do")
    public BaseResp delRecord(HttpServletRequest request, HttpServletResponse response) {
        int id = TextUtils.getParameter(request, "id", -1);
        BaseResp baseResp = new BaseResp();
        if (id > 0 && mEventService.deltetRecord(id) == 1) {
            baseResp.setCode(200);
            baseResp.setMsg("删除成功");
            return baseResp;
        }
        baseResp.setCode(500);
        baseResp.setMsg("删除失败");
        return baseResp;
    }

}
