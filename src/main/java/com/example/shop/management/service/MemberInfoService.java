package com.example.shop.management.service;

import com.example.shop.management.bean.LoginUser;
import com.example.shop.management.bean.MemberInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
public interface MemberInfoService extends IService<MemberInfo> {

    String registered(String account, String password,String code);

    boolean getVerificationCode(String account);

    MemberInfo findByUserName(String username);

    LoginUser login(MemberInfo user);
}
