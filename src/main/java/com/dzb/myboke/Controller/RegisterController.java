package com.dzb.myboke.Controller;

import com.dzb.myboke.Constant.CodeType;
import com.dzb.myboke.Constant.NameType;
import com.dzb.myboke.Service.UserService;
import com.dzb.myboke.Utils.DataMap;
import com.dzb.myboke.Utils.JsonResult;
import com.dzb.myboke.Utils.SHAUtil;
import com.dzb.myboke.Utils.TimeUtils;
import com.dzb.myboke.VO.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/3/31 23:21
 */
@Controller
@Slf4j
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String register(User user,
                           HttpServletRequest request) {
        if (userService.usernameIsExist(user.getUsername()) || user.getUsername().equals(NameType.ANONYMOUS_USER)) {
            log.error("[{}] username is exist or username is null", TimeUtils.getCurrentTime());
            return JsonResult.fail(CodeType.USERNAME_EXIST).toJSON();
        }
        SHAUtil shaUtil = new SHAUtil();
        user.setPassword(shaUtil.SHA256Encrypt(user.getPassword()));

        DataMap data = userService.insert(user);
        log.error("[{}] register is success", TimeUtils.getCurrentTime());
        return JsonResult.build(data).toJSON();
    }
}
