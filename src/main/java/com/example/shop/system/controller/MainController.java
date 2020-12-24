package com.example.shop.system.controller;

import com.example.shop.base.captcha.GifCaptcha;
import com.example.shop.base.json.ApiUtil;
import com.example.shop.base.json.Result;
import com.example.shop.pub.Utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
@Api(description = "后台图形验证码控制")
@Slf4j
@RestController
@RequestMapping("/system/captcha")
public class MainController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    private RedisUtils redisUtils;
    @ApiOperation(value = "设置头衔接口", notes = "设置头衔接口")
    @ApiImplicitParams({
           ,


    })
    @RequestMapping(path = "/", method = {RequestMethod.POST})
    public String GetToken(){
        return Result.Result("00000","获取token", ApiUtil.generateToken());
    }

    @ApiOperation(value = "获取图形验证码", notes = "获取图形验证码")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),

    })
    @ResponseBody
    @RequestMapping(path = "/login", method = {RequestMethod.GET})
    public void login(String token) {
        this.generateAndWrite(token);
    }

    /**
     * 生成并写到输出流
     * @param sessionKey
     */
    private void generateAndWrite(String sessionKey) {
        HttpSession httpSession = request.getSession(true);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");

        try {
            GifCaptcha gifCaptcha =  new GifCaptcha(114, 42, new Font("Consolas", Font.BOLD, 30), 100);
            gifCaptcha.out(response.getOutputStream());

            redisUtils.remove(sessionKey);
            redisUtils.removePattern(sessionKey);

            redisUtils.set(sessionKey,gifCaptcha.getWord(),60L);
            log.info((String)redisUtils.get(sessionKey));
            log.info(sessionKey);

        } catch (Exception e) {
            log.error("生成验证码出错", e);
        }
    }
}
