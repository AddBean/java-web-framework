package com.base.service;

import com.base.dao.UserDetailMapper;
import com.base.dao.UserMapper;
import com.base.entity.User;
import com.base.entity.UserDetail;
import com.base.utils.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
@Service
public class UserService {
    @Resource
    private UserMapper mUser;
    @Resource
    private UserDetailMapper mUserDetail;

    public boolean addUser(User user) {
        List<User> tempUser = mUser.selectByName(user.getUsername());
        //若该用户不存在
        if (tempUser == null || tempUser.size() == 0) {
            int result = mUser.insert(user);
            if (result == 1) {
                UserDetail userDetail = new UserDetail();
                userDetail.setUserId(mUser.selectByName(user.getUsername()).get(0).getId());
                mUserDetail.insert(userDetail);
            }

            return true;
        }
        return false;
    }

    public boolean checkLogin(User user) {
        List<User> userList = mUser.selectByName(user.getUsername());
        if (userList == null || userList.size() == 0) return false;
        return userList.get(0).getPasswordSalt().equals(user.getPasswordSalt());
    }

    public List getUserList(int index, int page) {
        return mUser.selectByPage(index * page, page);
    }

    public User getUserInf(String name) {
        List<User> userList = mUser.selectByName(name);

        if (userList != null || userList.size() > 0)
            return userList.get(0);
        else return null;
    }

    public int updateUser(User user) {
        mUser.updateByPrimaryKey(user);
        return mUser.updateByPrimaryKey(user);
    }


    public int deleteUser(int userId) {
        return mUser.deleteByPrimaryKey(userId);
    }

    public String updateToken(int userId) {
        User user = mUser.selectByPrimaryKey(userId);
        user.setToken(generateToken(user.getUsername()));
        mUser.updateByPrimaryKey(user);
        return user.getToken();
    }

    /**
     * 根据token查找user;
     *
     * @param token
     * @return
     */
    public User getUserByToken(String token) {
        return mUser.selectByToken(token);
    }

    public User getUserInfById(int id) {
        return mUser.selectByPrimaryKey(id);
    }

    /**
     * 生成token;
     *
     * @param userName
     * @return
     */
    private String generateToken(String userName) {
        String raw = userName + System.currentTimeMillis();
        return MD5Utils.String2md5(raw);
    }

}
