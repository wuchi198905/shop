package com.example.shop.base.json;

import java.util.Random;
import java.util.UUID;

public class ApiUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }
    /**
     * 生成随机短信验证码
     * @return
     */
    public static String generateSmsCode() {
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        buf.append(random.nextInt(9) + 1);
        for (int i = 0; i < 5; i++) {
            buf.append(random.nextInt(10));
        }
        return buf.toString();
    }
    /**
     * 生成token
     * @return
     */
    public static String generateToken() {
        return uuid();
    }
}
