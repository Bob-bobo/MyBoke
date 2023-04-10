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

package com.dzb.myboke.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/3/31 22:58
 */
@Data
@NoArgsConstructor
public class User {

    /**
     * 用户Id
     */
    private Integer Id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 个人爱好
     */
    private String personal;
    /**
     * 真实姓名
     */
    private String truename;
    /**
     * 最近登录
     */
    private String recentlogin;
    /**
     * 角色集合
     */
    private List<Role> roles;

    public User(String username, String phone, String password) {
        this.username = username;
        this.phone = phone;
        this.password = password;
    }

    public User(Integer id, String username, Integer age, String phone, String password, String email, String avatar,
                String birthday, String personal, String truename, String recentlogin, List<Role> roles) {
        Id = id;
        this.username = username;
        this.age = age;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.birthday = birthday;
        this.personal = personal;
        this.truename = truename;
        this.recentlogin = recentlogin;
        this.roles = roles;
    }
}
