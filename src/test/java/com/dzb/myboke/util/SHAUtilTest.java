package com.dzb.myboke.util;

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
        System.out.println(SHAEncrypt(s));
        System.out.println(encryptSHA(s.getBytes()));
        System.out.println(SHA256Encrypt(s));
    }

    @Test
    public void testFanEncrypt() {
        String s = "c5558fc574991705eff25fb3e4c8c0d9c4c95a17";

    }
}
