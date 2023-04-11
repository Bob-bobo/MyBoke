package com.dzb.myboke.Controller;

import com.dzb.myboke.Utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/4/10 23:39
 */
@Controller
@Slf4j
public class LoginController {

    @PostMapping("/login-error")
    public String loginError() {
        log.info("[{}]: login is error in the web", TimeUtils.getCurrentTime());
        return "login";
    }
}
