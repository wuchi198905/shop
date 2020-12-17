package com.example.shop.base.shiro;


import com.example.shop.base.jwt.JwtToken;
import com.example.shop.base.util.JwtUtil;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.management.service.MemberInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MyRealm:自定义一个授权
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/12
 */

@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private MemberInfoService memberInfoService;

    /**
     * 必须重写此方法，不然Shiro会报错
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JwtUtil.getUsername(principals.toString());
        MemberInfo user = memberInfoService.findByUserName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = null;
        try {
            //这里工具类没有处理空指针等异常这里处理一下(这里处理科学一些)
            username = JwtUtil.getUsername(token);
        } catch (Exception e) {
            throw new AuthenticationException("heard的token拼写错误或者值为空");
        }
        if (username == null) {
          log.error("token无效(空''或者null都不行!)");
            throw new AuthenticationException("token无效");
        }
        MemberInfo userBean = memberInfoService.findByUserName(username);
        if (userBean == null) {
            log.error("用户不存在!)");
            throw new AuthenticationException("用户不存在!");
        }
        if (!JwtUtil.verify(token, username, userBean.getPassword())) {
            log.error("用户名或密码错误(token无效或者与登录者不匹配)!)");
            throw new AuthenticationException("用户名或密码错误(token无效或者与登录者不匹配)!");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
