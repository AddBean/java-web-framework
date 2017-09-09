package com.base.controller.web;

import com.base.entity.Event;
import com.base.entity.Record;
import com.base.entity.condition.BaseCondition;
import com.base.entity.condition.EventCondition;
import com.base.entity.condition.RecordCondition;
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
import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */
@Controller
@RequestMapping("/web")
public class EventController {

    @Resource
    protected EventService mEventService;

    @ResponseBody
    @RequestMapping(value = "/event/getEventList.do")
    public List<Event> getEventList(HttpServletRequest request, HttpServletResponse response) {
        EventCondition condition = getConditionEntity(request, new EventCondition());
        String name = TextUtils.getParameter(request, "name", "");
        String type = TextUtils.getParameter(request, "type", "");
        String des = TextUtils.getParameter(request, "des", "");
        condition.setName(name);
        condition.setType(type);
        condition.setDes(des);
        System.out.print(new Gson().toJson(condition) + "\n");
        return mEventService.getEventList(condition);
    }

    @ResponseBody
    @RequestMapping(value = "/event/getEvenRecordList.do")
    public List<Record> getEvenRecordList(HttpServletRequest request, HttpServletResponse response) {
        RecordCondition condition = getConditionEntity(request, new RecordCondition());
        condition.setName(TextUtils.getParameter(request, "name", ""));
        condition.setDetail(TextUtils.getParameter(request, "detail", ""));
        condition.setEx(TextUtils.getParameter(request, "ex", ""));
        condition.setIp(TextUtils.getParameter(request, "ip", ""));
        condition.setEx1(TextUtils.getParameter(request, "ex1", ""));
        condition.setUuid(TextUtils.getParameter(request, "uuid", ""));
        condition.setSysVer(TextUtils.getParameter(request, "sysVer", ""));
        if (TextUtils.hasParameter(request, "userId")) {
            Integer userId = TextUtils.getParameter(request, "userId", 0);
            condition.setUserId(userId);
        }
        System.out.print(new Gson().toJson(condition) + "\n");
        return mEventService.getEventRecordList(condition);
    }

    /**
     * 自动装配基础查询参数；
     *
     * @param request
     * @param entity
     * @param <T>
     * @return
     */
    public <T extends BaseCondition> T getConditionEntity(HttpServletRequest request, T entity) {
        try {
            if (TextUtils.hasParameter(request, "page")) {
                int page = TextUtils.getParameter(request, "page", 0);
                int pagesize = TextUtils.getParameter(request, "pagesize", 0);
                entity.setcIndex((page - 1) * pagesize);
                entity.setcOffset(pagesize);
            }else{
                entity.setcIndex(-1);
            }
            if (TextUtils.hasParameter(request, "desc"))
                entity.setcDesc(TextUtils.getParameter(request, "desc", true));
            if (TextUtils.hasParameter(request, "startTime"))
                entity.setcStartTime(new Date(TextUtils.getParameter(request, "startTime", new Long(0))));
            if (TextUtils.hasParameter(request, "endTime"))
                entity.setcEndTime(new Date(TextUtils.getParameter(request, "endTime", new Long(0))));
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
}
