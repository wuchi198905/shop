package com.example.shop.system.controller;


import com.example.shop.base.json.ApiUtil;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.DTO.FuncListDTO;
import com.example.shop.management.bean.LoginUser;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.Utils.RedisUtils;
import com.example.shop.system.bean.DTO.UserInfoDTO;
import com.example.shop.system.bean.UserInfo;
import com.example.shop.system.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-22
 */
@Slf4j
@Api(description = "后台用户登录控制")
@RestController
@RequestMapping("/system/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisUtils redisUtils;
    @ApiOperation(value = "登录接口", notes = "登录系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "username", value = "username", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "password", value = "username", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "code", value = "验证码", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/login", method = {RequestMethod.POST})
    public String login(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getParameter("token");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String  code= request.getParameter("code");

        if(!redisUtils.exists(token)){
            return Result.Result("30004","验证码已失效请重新获取", ApiUtil.generateToken());
        }
        String  oldcode= (String) redisUtils.get(token);
       log.info(oldcode);
       log.info(token);
        if(StringUtils.isBlank(code)){
            return Result.Result("30002","验证码不能为空", ApiUtil.generateToken());
        }

        if(!StringUtils.equalsIgnoreCase(code, oldcode)){
            return Result.Result("30001","验证码不正确请重新获取", ApiUtil.generateToken());
        }
        UserInfo memberInfo=new UserInfo();
        memberInfo.setPassword(password);
        memberInfo.setUsername(username);
        LoginUser loginUser=new LoginUser();
         loginUser = userInfoService.login(memberInfo);
        redisUtils.remove(token);
         if(loginUser.isLogin()){
             return Result.Result(RC.SUCCESS,loginUser.getToken());
         }
        return Result.Result(RC.LOGIN_USER_ISNOT);
    }

    @ApiOperation(value = "获取菜单", notes = "获取菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true)
    })
    @ResponseBody
    @RequestMapping(path = "/main/buildMenu", method = {RequestMethod.POST})
    public String buildMenu(UserInfoDTO userInfo) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            List<FuncListDTO> funcList = userInfoService.buildMenu();

            // 整理回传数据
            for (FuncListDTO func : funcList) {
                Map<String, Object> m1 = new HashMap<String, Object>();
                m1.put("id", func.getFuncId());
                m1.put("title", func.getMenuTitle());
                m1.put("icon", func.getMenuIcon());
                m1.put("href", func.getMenuUrl());

                List<Map<String, Object>> subList = new ArrayList<Map<String, Object>>();
                for (FuncListDTO subFunc : func.getSubFuncList()) {
                    Map<String, Object> m2 = new HashMap<String, Object>();
                    m2.put("id", subFunc.getFuncId());
                    m2.put("title", subFunc.getMenuTitle());
                    m2.put("icon", subFunc.getMenuIcon());
                    m2.put("href", subFunc.getMenuUrl());

                    List<Map<String, Object>> subList3 = new ArrayList<Map<String, Object>>();
                    for (FuncListDTO subFunc3 : subFunc.getSubFuncList()) {
                        Map<String, Object> m3 = new HashMap<String, Object>();
                        m3.put("id", subFunc3.getFuncId());
                        m3.put("title", subFunc3.getMenuTitle());
                        m3.put("icon", subFunc3.getMenuIcon());
                        m3.put("href", subFunc3.getMenuUrl());
                        m3.put("children", new ArrayList<>());

                        subList3.add(m3);

                    }

                    m2.put("children", subList3);
                    subList.add(m2);

                }
                m1.put("menu", subList);
                list.add(m1);
            }

            // 默认选中第一条
            if (list.size() > 0 && list.get(0).size() > 0) {
                list.get(0).put("isCurrent", true);
            }
        } catch (Exception e) {
           // return callbackException(logger, "查询用户菜单权限出错", e);
        }
        return Result.Result(RC.SUCCESS,list);
    }

}

