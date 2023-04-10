/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dzb.myboke.Controller;

import com.dzb.myboke.Utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
@Slf4j
public class BasicController {
    /**
     * 跳转首页
     * @return
     */
    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("lastUrl", (String) request.getSession().getAttribute("lastUrl"));
        return "login";
    }
    /**
     * 登录页面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        log.info("[{}]: is log in the web", TimeUtils.getCurrentTime());
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        log.info("[{}]: is register in the web", TimeUtils.getCurrentTime());
        return "register";
    }

    @GetMapping("/forget")
    public String forget() {
        log.info("[{}]: is forget in the web", TimeUtils.getCurrentTime());
        return "forget";
    }
}
