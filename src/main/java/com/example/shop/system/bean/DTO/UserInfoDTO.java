package com.example.shop.system.bean.DTO;

import com.example.shop.system.bean.UserInfo;
import lombok.Data;

@Data
public class UserInfoDTO extends UserInfo {
    private String captcha;
    private String password2;
    private String oldPassword;

    private String custName;
}
