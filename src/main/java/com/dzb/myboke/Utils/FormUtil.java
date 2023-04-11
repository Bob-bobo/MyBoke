package com.dzb.myboke.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/4/10 23:12
 */
public class FormUtil {
    /**
     * 判断是否为手机号
     * @param consumer
     * @return
     */
    public boolean isMobile(String consumer)
    {
        Pattern pattern = null;
        Matcher matcher = null;
        boolean flag = false;
        pattern = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        matcher = pattern.matcher(consumer);
        flag = matcher.matches();
        return flag;
    }

    public boolean iseMail(String consumer) {
        return consumer.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    }
}
