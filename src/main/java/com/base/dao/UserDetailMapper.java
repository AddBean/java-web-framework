package com.base.dao;

import com.base.entity.UserDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDetail record);

    int insertSelective(UserDetail record);

    UserDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDetail record);

    int updateByPrimaryKey(UserDetail record);

    UserDetail selectByUserId(Integer id);
}