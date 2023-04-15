package com.dzb.myboke.Controller;

import com.dzb.myboke.Constant.CodeType;
import com.dzb.myboke.Dao.UserMapper;
import com.dzb.myboke.Service.Impl.UserServiceImpl;
import com.dzb.myboke.Service.UserService;
import com.dzb.myboke.Utils.JsonResult;
import com.dzb.myboke.Utils.SHAUtil;
import com.dzb.myboke.Utils.TimeUtils;
import com.dzb.myboke.VO.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/4/10 23:39
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/change-password")
    public String changePassword(@Param("consumer") String consumer,
                               @Param("password1") String password1,
                               @Param("password2") String password2) {
        if (null == password1 || null == password2 || !password1.equals(password2)) {
            log.error("[{}]: change the password is error or not equals", TimeUtils.getCurrentTime());
            return JsonResult.fail(CodeType.CONSUMER_IS_ERROR).toJSON();
        }

        User user = userService.getUserByConsumer(consumer);
        if (null == user) {
            log.error("[{}]: the consumer is error or not exists", TimeUtils.getCurrentTime());
            return JsonResult.fail(CodeType.USERNAME_EXIST).toJSON();
        }

        SHAUtil shaUtil = new SHAUtil();
        userMapper.updatePassword(shaUtil.SHAEncrypt(password1), user.getPhone());

        return JsonResult.success().toJSON();
    }
}
