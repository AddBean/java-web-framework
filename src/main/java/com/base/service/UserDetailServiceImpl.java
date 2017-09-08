package com.base.service;

import com.base.dao.UserDetailMapper;
import com.base.entity.UserDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/7.
 */
@Service
public class UserDetailServiceImpl   {
    @Resource
    private UserDetailMapper mUserDetailMapper;

    public int deleteUserDetail(int userDetailId) {
        return mUserDetailMapper.deleteByPrimaryKey(userDetailId);
    }

    public int addUserDetail(UserDetail detail) {
        return mUserDetailMapper.insert(detail);
    }

    public int updateUserDetail(UserDetail detail) {
        return mUserDetailMapper.updateByPrimaryKey(detail);
    }

    public UserDetail getUserDetailByUserId(int id) {
        return mUserDetailMapper.selectByUserId(id);
    }
}
