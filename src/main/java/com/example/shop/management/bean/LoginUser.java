package com.example.shop.management.bean;

import com.example.shop.system.bean.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class LoginUser{
    /**
     * 验证码
     */
    private String phoneCode;
    /**
     * 用户
     */
    private MemberInfo user;
    private UserInfo userInfo;

    /**
     * 用户token验证(header的键名)
     */
    private String token;
    /**
     *
     */
    private boolean login;


}
