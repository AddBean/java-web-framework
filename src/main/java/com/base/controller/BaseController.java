package com.base.controller;

import com.base.service.UserServiceImpl;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/7.
 */
public abstract class BaseController {
    @Resource
    protected UserServiceImpl mUserServiceImpl;

    protected UserServiceImpl getmUserService() {
        return mUserServiceImpl;
    }
}
