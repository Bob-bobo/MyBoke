package com.dzb.myboke.Config;

import com.dzb.myboke.Service.Impl.CustomUserServiceImpl;
import com.dzb.myboke.Utils.SHAUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/4/1 15:27
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() {
        return new CustomUserServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许所有用户访问"/"和"/home"
        http.authorizeRequests()
                .antMatchers("/login","/register").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")  //指定登录页是"/login"
                .defaultSuccessUrl("/list")  //登录成功后默认跳转到"list"
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")  //退出登录后的默认url是"/home"
                .permitAll()
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService())
                .passwordEncoder(new PasswordEncoder() {
                    SHAUtil shaUtil = new SHAUtil();
                    @Override
                    public String encode(CharSequence rawPassword) {
                        return shaUtil.SHA256Encrypt((String) rawPassword);
                    }

                    @Override
                    public boolean matches(CharSequence rawPassword, String encodedPassword) {
                        return encodedPassword.equals(encode(rawPassword));
                    }
                });
    }
}