package com.example.shop.pub.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.shop.base.json.ApiUtil;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.base.util.JwtUtil;
import com.example.shop.management.bean.DTO.MemberInfoDTO;
import com.example.shop.management.bean.LoginUser;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.mapper.MemberInfoMapper;
import com.example.shop.pub.service.IMailService;
import com.example.shop.pub.service.MemberInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.shop.pub.Utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private IMailService iMailService;
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

                memberInfo.setAccount(account);
                memberInfo=memberInfoMapper.selectOne(memberInfo);
                String token = JwtUtil.sign(account, password);
                redisUtils.set(token,memberInfo,3600L);

                return Result.Result(RC.SUCCESS,token);
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
        iMailService.sendSimpleMail("1962797941@qq.com","验证码","注册验证码是"+code+"，请不要告诉其他人");
//        SendSmsResponse result=SmsUtil.sendCheckcode(account,code);
//        if (!StringUtils.equals("OK", result.getCode())) {
//            log.error("发送短信验证码出错：" + result.getCode() + ", " + result.getMessage());
//            //throwSysException(RC.OTHER_SMSCODE_ERROR);
//           return false;
//        }
        redisUtils.set(account,code,60L);
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

        LoginUser loginUser=new LoginUser();
        loginUser.setUser(user);
        //根据电话号码和密码加密生成token
        String token = JwtUtil.sign(user.getAccount(), user.getPassword());
        redisUtils.set(token,user,3600L);
        loginUser.setToken(token);
        return loginUser;
    }

    @Override
    public List<MemberInfoDTO> HomepageDisplayPagination() {
        MemberInfoDTO memberInfoDTO=new MemberInfoDTO();
        return memberInfoMapper.HomepageDisplayPagination(memberInfoDTO);
    }

    @Override
    public List<MemberInfoDTO> Latestregisteredmembers() {
        return memberInfoMapper.Latestregisteredmembers();
    }

    @Override
    public List<MemberInfo> MenuPagination(MemberInfo memberInfo) {
        return memberInfoMapper.getmemberInfopage(memberInfo);
    }

    @Override
    public String coderegistered(String account, String code) {
        String codes=(String)redisUtils.get(account);
        if(codes==null){
            return Result.Result(RC.REGIST_PARAM_SMSCODE_INVALID);
        }else{
            if(code.equals(code)){
                int fig=memberInfoMapper.selectCount(new EntityWrapper<MemberInfo>().eq("account",account));
                if(fig==0){
                    MemberInfo memberInfo=new MemberInfo();
                    memberInfo.setPassword("123456");
                    memberInfo.setAccount(account);
                    memberInfo.setRegistrationTime(new Date());
                    memberInfoMapper.insert(memberInfo);
                }
                MemberInfo memberInfo=memberInfoMapper.selectById(new EntityWrapper<MemberInfo>().eq("account",account));
                String token = JwtUtil.sign(account,memberInfo.getPassword());
                redisUtils.set(token,memberInfo,3600L);
                return Result.Result(RC.SUCCESS,token);
            }else{
                return Result.Result(RC.REGIST_PARAM_SMSCODE_INVALID);
            }
        }
    }
}
