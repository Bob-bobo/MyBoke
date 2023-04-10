package com.dzb.myboke.Service.Impl;

import com.dzb.myboke.Constant.CodeType;
import com.dzb.myboke.Constant.NameType;
import com.dzb.myboke.Dao.UserMapper;
import com.dzb.myboke.Service.UserService;
import com.dzb.myboke.Utils.DataMap;
import com.dzb.myboke.VO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/3/31 23:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserByPhone(String phone) {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean usernameIsExist(String username) {
        return false;
    }

    @Override
    public DataMap insert(User user) {
        user.setUsername(user.getUsername().trim().replaceAll("", NameType.BLANK));
        String userName = user.getUsername();
        // 查询注册用户名是否合理
        if (userName.length() > NameType.USERNAME_MAX_LENGTH || NameType.BLANK.equals(userName)) {
            return DataMap.fail(CodeType.USERNAME_FROMAT_ERROR);
        }
        // 查询手机号是否已注册
        if (userIsExist(user.getPhone())) {
            return DataMap.fail(CodeType.PHONE_IS_REGISTERED);
        }
        // 随机生成头像
        Random r = new Random();
        user.setAvatar("https://mybobbucket.oss-cn-beijing.aliyuncs.com/myweb/userAvatar/"+r.nextInt(13)+".jpg");

        userMapper.save(user);
        int user_id = userMapper.findIdByPhone(user.getPhone());
        userMapper.insertRole(user_id, NameType.DOMESTIC_USER);
        return DataMap.success();
    }

    @Override
    public boolean userIsExist(String phone) {
        User user = userMapper.getUserByPhone(phone);
        return user == null;
    }


}
