package com.dzb.myboke.util;

import com.dzb.myboke.Utils.FormUtil;
import org.junit.jupiter.api.Test;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/4/12 23:27
 */
public class FormUtilTest {

    @Test
    public void testIsEmail() {
        String name = "四川彭于晏";
        FormUtil formUtil = new FormUtil();
        if (formUtil.iseMail(name)) {
            System.out.println("属于邮箱");
        }
    }
}
