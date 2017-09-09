package com.base.controller;

import com.base.service.UserService;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/7.
 */
public abstract class BaseController {
    @Resource
    protected UserService mUserServiceImpl;

    protected UserService getmUserService() {
        return mUserServiceImpl;
    }
}
