package com.base.service;

import com.base.dao.RoleMapper;
import com.base.entity.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/9/7.
 */
@Service
public class RoleService {
    @Resource
    private RoleMapper mRoleMapper;

    public List<Role> getRoleList() {
        return null;
    }

    public Role getRole(int roleId) {
        return mRoleMapper.selectByPrimaryKey(roleId);
    }

    public Role addRole(int roleId) {
        return null;
    }
}
