package com.example.shop.base.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.example.shop.base.SessionVehicle;
import com.example.shop.base.jwt.JwtToken;
import com.example.shop.base.result.ResponseData;
import com.example.shop.base.result.ResponseDataUtil;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.Utils.RedisUtils;
import com.example.shop.system.bean.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * JwtFilter:jwt过滤器来作为shiro的过滤器
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/12
 */
@Slf4j
@Component//这个注入与否影响不大
public class JwtFilter extends BasicHttpAuthenticationFilter implements Filter {
    @Autowired
    private RedisUtils redisUtils;
    private static JwtFilter commonUtil;

    @PostConstruct
    public void init() {
        commonUtil = this;
        System.err.println("测试一下***********");
    }

    /**
     * 执行登录
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Token");
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest req =attr.getRequest();
            String pathString=req.getServletPath();



            String strDirPath = request.getServletContext().getRealPath("/");
            log.info("当前请求的接口"+strDirPath);
            String [] pathStrings=pathString.split("/");
            if(pathStrings[1].equals("memberInfo")){
                log.info(strDirPath);
                getSubject(request, response).login(jwtToken);
                MemberInfo memberInfoMVO = (MemberInfo) commonUtil.redisUtils.get(token);
                SessionVehicle.reflesh(memberInfoMVO);
                return true;
            }if(pathStrings[1].equals("system")){
                getSubject(request, response).login(jwtToken);
                UserInfo memberInfoMVO = (UserInfo) commonUtil.redisUtils.get(token);
                return true;
            }
            if(pathStrings[1].equals("api")){
                getSubject(request, response).login(jwtToken);
                return true;
            }else {
                return false;
            }


        } catch (AuthenticationException e) {
              ResponseData responseData = ResponseDataUtil.authorizationFailed( "没有访问权限，原因是:" + e.getMessage());
//            //SerializerFeature.WriteMapNullValue为了null属性也输出json的键值对
            Object o = JSONObject.toJSONString(responseData, SerializerFeature.WriteMapNullValue);

            response.setCharacterEncoding("utf-8");
            response.getWriter().print(o);
            return false;
        }

    }

    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            return executeLogin(request, response);
            // return true;有一篇博客这里直接返回true是不正确的,在这里我特别指出一下
        } catch (Exception e) {
            log.error("JwtFilter过滤验证失败!");
            return false;
        }
    }


    /**
     * 对跨域提供支持
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
