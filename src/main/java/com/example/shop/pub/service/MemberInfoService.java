package com.example.shop.pub.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.shop.management.bean.DTO.MemberInfoDTO;
import com.example.shop.management.bean.LoginUser;
import com.example.shop.management.bean.MemberInfo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
public interface MemberInfoService extends IService<MemberInfo> {

    String registered(String account,String userName, String password,String code);

    boolean getVerificationCode(String account);

    MemberInfo findByUserName(String username);

    LoginUser login(MemberInfo user);

    List<MemberInfoDTO> HomepageDisplayPagination();

    List<MemberInfoDTO> Latestregisteredmembers();

    List<MemberInfo> MenuPagination(MemberInfo memberInfo);

    String coderegistered(String account, String code);

    List<MemberInfoDTO> selectUserListPage(Page<MemberInfoDTO> page,MemberInfoDTO memberInfoDT);

    MemberInfoDTO selectUserInfo(MemberInfoDTO memberInfoDTO);
}
