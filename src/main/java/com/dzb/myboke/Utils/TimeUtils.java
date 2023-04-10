package com.dzb.myboke.Utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/4/1 17:25
 */
@Component
public class TimeUtils {
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        return sdf.format(date);
    }
}
