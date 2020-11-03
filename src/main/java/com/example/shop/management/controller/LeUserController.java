package com.example.shop.management.controller;


import com.example.shop.management.bean.LeUser;
import com.example.shop.management.service.LeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-11-03
 */
@RestController
@RequestMapping("/leUser")
public class LeUserController {
    @Autowired
   private LeUserService leUserService;
    /**
     * 登录
     * @author yanziyang
     * @date 2018年5月23日09:43:10
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(LeUser member){
        try {
            //leUserService.login(member);
        }catch (Exception e){

        }
        return (String)null;
    }
}

