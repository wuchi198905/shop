package com.example.shop.management.service.impl;

import com.example.shop.base.json.ApiUtil;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.base.util.JwtUtil;
import com.example.shop.management.bean.LoginUser;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.management.mapper.MemberInfoMapper;
import com.example.shop.management.service.MemberInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.shop.pub.Utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Slf4j
@Service
public class MemberInfoServiceImpl extends ServiceImpl<MemberInfoMapper, MemberInfo> implements MemberInfoService {
    @Autowired
    private  RedisUtils redisUtils;
    @Autowired
    private   MemberInfoMapper memberInfoMapper;
    @Override
    public String registered(String account, String password,String code) {
        String codes=(String)redisUtils.get(account);
        if(codes==null){
            return Result.Result(RC.REGIST_PARAM_SMSCODE_INVALID);
        }else{
            if(code.equals(code)){
                MemberInfo memberInfo=new MemberInfo();
                memberInfo.setPassword(password);
                memberInfo.setAccount(account);
                memberInfo.setRegistrationTime(new Date());
                memberInfoMapper.insert(memberInfo);

                return Result.Result(RC.SUCCESS);
            }else{
                return Result.Result(RC.REGIST_PARAM_SMSCODE_INVALID);
            }
        }


    }

    @Override
    public boolean getVerificationCode(String account) {
        String code=ApiUtil.generateSmsCode();
        MemberInfo memberInfo=new MemberInfo();
        memberInfo.setAccount(account);

           log.info("验证犸是   "+code);
//        SendSmsResponse result=SmsUtil.sendCheckcode(account,code);
//        if (!StringUtils.equals("OK", result.getCode())) {
//            log.error("发送短信验证码出错：" + result.getCode() + ", " + result.getMessage());
//            //throwSysException(RC.OTHER_SMSCODE_ERROR);
//           return false;
//        }
        redisUtils.set(account,code,3600L);
        return true;
    }

    @Override
    public MemberInfo findByUserName(String username) {
        MemberInfo memberInfo=new MemberInfo();
        memberInfo.setAccount(username);
        memberInfo=memberInfoMapper.selectOne(memberInfo);
        return memberInfo;
    }

    @Override
    public LoginUser login(MemberInfo user) {
        Map<String, Object> map = new HashMap<>();
        map.put("account", user.getAccount());
        map.put("password", user.getPassword());
        //throw  new MyException("我的模拟业务代码的异常!");
        MemberInfo user1 = null;
        try {
            user1 = memberInfoMapper.selectByMap(map).get(0);
            user1.setLastLoginTime(new Date());
            memberInfoMapper.updateById(user1);
        } catch (Exception e) {
           // throw new MyException("该用户名或者密码错误,请检查后再登录!");
        }
        LoginUser loginUser=new LoginUser();
        loginUser.setUser(user1);
        //根据电话号码和密码加密生成token
        String token = JwtUtil.sign(user1.getAccount(), user1.getPassword());
        redisUtils.set(token,user1,10000L);
        loginUser.setToken(token);
        return loginUser;
    }
}
