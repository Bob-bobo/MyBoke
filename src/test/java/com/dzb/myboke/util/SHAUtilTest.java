package com.dzb.myboke.util;

import com.dzb.myboke.Utils.SHAUtil;
import org.junit.jupiter.api.Test;

import static com.dzb.myboke.Utils.SHAUtil.*;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/4/1 0:53
 */
public class SHAUtilTest {

    @Test
    public void testSHAEncrypt() throws Exception {
        String s = "dzb19980320DZY";
        SHAUtil shaUtil = new SHAUtil();
//        System.out.println(shaUtil.SHAEncrypt(s));
//        System.out.println(shaUtil.encryptSHA(s.getBytes()));
//        System.out.println(shaUtil.SHA256Encrypt(s));
        System.out.println(shaUtil.SHA256Encrypt("123456"));
    }

    @Test
    public void testFanEncrypt() {
        String s = "c5558fc574991705eff25fb3e4c8c0d9c4c95a17";
        String ss = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
        String se = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
        System.out.println(ss.equals(se));
    }
}
