package com.example.shop.management.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class LoginUser extends MemberInfo{
    /**
     * 验证码
     */
    private String phoneCode;
    /**
     * 用户
     */
    private MemberInfo user;

    /**
     * 用户token验证(header的键名)
     */
    private String token;
}
