package com.dzb.myboke.Utils;

import com.dzb.myboke.Constant.CodeType;
import com.dzb.myboke.Constant.NameType;
import com.dzb.myboke.Constant.anno.PermissionCheck;
import com.sun.webkit.Timer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/4/1 0:00
 */
@Aspect
@Component
@Slf4j
public class PrincipalAspect {

    private static final String ANONYMOUS_USER = NameType.ANONYMOUS_USER;

    @Pointcut("execution(public * com.dzb.myboke.Controller..*(..))")
    public void login() {
    }

    @Around("login() && @annotation(permissionCheck)")
    public Object principalAround(ProceedingJoinPoint point, PermissionCheck permissionCheck) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginName = authentication.getName();
        if (loginName.equals(ANONYMOUS_USER)) {
            return JsonResult.fail(CodeType.USER_NOT_LOGIN).toJSON();
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String value = permissionCheck.value();
        for (GrantedAuthority g : authorities) {
            if (g.getAuthority().equals(value)) {
                return point.proceed();
            }
        }
        log.error("[{}]:[{}] not login in the [{}]", Timer.getTimer(),loginName, point.getSignature().getName());
        return JsonResult.fail(CodeType.PERMISSION_VERIFY_FAIL).toJSON();
    }
}
