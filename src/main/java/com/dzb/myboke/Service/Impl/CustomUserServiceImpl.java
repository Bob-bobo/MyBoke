package com.dzb.myboke.Service.Impl;

import com.dzb.myboke.Service.UserService;
import com.dzb.myboke.Utils.FormUtil;
import com.dzb.myboke.Utils.TimeUtils;
import com.dzb.myboke.VO.Role;
import com.dzb.myboke.VO.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/4/1 16:19
 */
@Slf4j
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String consumer) throws UsernameNotFoundException {

        User user = userService.getUserByConsumer(consumer);

        if (user == null) {
            log.info("[{}]: no have user log in the web ", TimeUtils.getCurrentTime());
            throw new UsernameNotFoundException("用户不存在");
        }

        TimeUtils timeUtils = new TimeUtils();
        long recentlyLogin = timeUtils.getCurrentDate();
        userService.updateRecentLogin(user.getUsername(), recentlyLogin);
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        for (Role role : user.getRoles()) {
            authorityList.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorityList);
    }
}
