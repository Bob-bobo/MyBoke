package com.dzb.myboke.Service.Impl;

import com.dzb.myboke.Constant.CodeType;
import com.dzb.myboke.Constant.NameType;
import com.dzb.myboke.Dao.UserMapper;
import com.dzb.myboke.Service.UserService;
import com.dzb.myboke.Utils.DataMap;
import com.dzb.myboke.Utils.FormUtil;
import com.dzb.myboke.Utils.TimeUtils;
import com.dzb.myboke.VO.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/3/31 23:24
 */
@Service
@Slf4j
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
            log.error("[{}] register username is unreasonable", TimeUtils.getCurrentTime());
            return DataMap.fail(CodeType.USERNAME_FROMAT_ERROR);
        }
        // 查询手机号是否已注册
        if (userIsExist(user.getPhone())) {
            log.error("[{}] register the phone is already registered", TimeUtils.getCurrentTime());
            return DataMap.fail(CodeType.PHONE_IS_REGISTERED);
        }
        // 随机生成头像
        Random r = new Random();
        user.setAvatar("https://mybobbucket.oss-cn-beijing.aliyuncs.com/myweb/userAvatar/"+r.nextInt(13)+".jpg");

        if (user.getUsername() == null) {
            user.setUsername("");
        } else if (user.getEmail() == null) {
            user.setEmail("");
        }
        TimeUtils timeUtils = new TimeUtils();
        user.setRecent_login(String.valueOf(timeUtils.getCurrentDate()));
        userMapper.saveUser(user);
        int user_id = userMapper.findIdByPhone(user.getPhone());
        userMapper.insertRole(user_id, NameType.DOMESTIC_USER);
        return DataMap.success();
    }

    @Override
    public boolean userIsExist(String phone) {
        User user = userMapper.getUserByPhone(phone);
        return user != null;
    }

    @Override
    public User getUserByConsumer(String consumer) {

        FormUtil formUtil = new FormUtil();

        if (formUtil.isMobile(consumer)) {
            User user = userMapper.getUserByPhone(consumer);
            log.info("[{}]: is login use the phone", TimeUtils.getCurrentTime());
            return user;
        }

        if (formUtil.iseMail(consumer)) {
            User user = userMapper.getUserByEmail(consumer);
            log.info("[{}]: is login use the email", TimeUtils.getCurrentTime());
            return user;
        }

        User user = userMapper.getUserByUsername(consumer);
        log.info("[{}]: is login use the username", TimeUtils.getCurrentTime());
        return user;
    }

    @Override
    public void updateRecentLogin(String consumer, long recent_login) {
        FormUtil formUtil = new FormUtil();
        String phone = null;
        if (formUtil.iseMail(consumer)) {
            User user = userMapper.getUserByEmail(consumer);
            log.info("[{}]: update the login time by the phone", TimeUtils.getCurrentTime());
            phone = user.getPhone();
        } else if (!formUtil.isMobile(consumer)) {
            log.info("[{}]: update the login time by the username", TimeUtils.getCurrentTime());
            User user = userMapper.getUserByUsername(consumer);
            phone = user.getPhone();
        } else {
            phone = consumer;
        }
        userMapper.updateRecentLogin(phone, recent_login);
    }


}
