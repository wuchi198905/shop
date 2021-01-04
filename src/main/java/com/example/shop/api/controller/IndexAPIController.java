package com.example.shop.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shop.api.constant.CommonWechatUtil;
import com.example.shop.api.constant.ConstantWeChat;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.Utils.DateUtil;
import com.example.shop.pub.Utils.RedisUtils;
import com.example.shop.pub.service.MemberInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(description = "微信登录接口")
@Slf4j
@Controller
@RequestMapping(value="/api/wsc")
public class IndexAPIController {
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    /**
     * 授权接口
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/index")
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE";
        String openid = request.getParameter("openid");
        if (openid == null) {
            openid = "0";
        }

        // 这个url的域名必须要进行再公众号中进行注册验证，这个地址是成功后的回调地址
        String urls = "http://www.hbtchyl.com/weishangcheng/api/wsc/login.html";
        //String urls = "http://s3uwnv.natappfree.cc/weishangcheng/api/wsc/login.html";
        urls = URLEncoder.encode(urls, "utf-8");
        // 第一步：用户同意授权，获取code
        String getCodeUrl = url.replace("APPID", ConstantWeChat.APPID).replace("REDIRECT_URI", urls).replace("SCOPE", ConstantWeChat.SCOPE)+"&state="+openid+"#wechat_redirect";
        log.info("获取code, getCodeUrl=" + getCodeUrl);
        // response.sendRedirect(url);
        response.sendRedirect(getCodeUrl);// 必须重定向，否则不能成功
    }

    /***
     * 授权登录接口
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void callBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("进callBack");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        // 第二步：通过code换取网页授权access_token
        String getTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + ConstantWeChat.APPID + "&secret="
                + ConstantWeChat.APPSECRET + "&code=" + code + "&grant_type=authorization_code";

        log.info("获取token,getTokenUrl=" + getTokenUrl);
        JSONObject getTokenJson = CommonWechatUtil.doGetJson(getTokenUrl);
        /*
         * { "access_token":"ACCESS_TOKEN", "expires_in":7200,
         * "refresh_token":"REFRESH_TOKEN", "openid":"OPENID", "scope":"SCOPE" }
         */
        log.info("获取token,getTokenJson=" + getTokenJson.toJSONString());

        String openid = getTokenJson.getString("openid");
        String access_token = getTokenJson.getString("access_token");
        String refresh_token = getTokenJson.getString("refresh_token");

        // 第五步验证access_token是否失效；展示都不需要
        String vlidTokenUrl = "https://api.weixin.qq.com/sns/auth?access_token=" + access_token + "&openid=" + openid;
        log.info("验证token,vlidTokenUrl=" + vlidTokenUrl);
        JSONObject validTokenJson = CommonWechatUtil.doGetJson(vlidTokenUrl);
        log.info("验证token,validTokenJson=" + validTokenJson.toJSONString());
        if (!"0".equals(validTokenJson.getString("errcode"))) {
            // 第三步：刷新access_token（如果需要）-----暂时没有使用,参考文档https://mp.weixin.qq.com/wiki，
            String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + openid
                    + "&grant_type=refresh_token&refresh_token=" + refresh_token;
            log.info("刷新token,refreshTokenUrl=" + refreshTokenUrl);
            JSONObject refreshTokenJson = CommonWechatUtil.doGetJson(refreshTokenUrl);
            /*
             * { "access_token":"ACCESS_TOKEN", "expires_in":7200,
             * "refresh_token":"REFRESH_TOKEN", "openid":"OPENID", "scope":"SCOPE" }
             */
            log.info("刷新token,refreshTokenJson=" + refreshTokenJson.toJSONString());
            access_token = refreshTokenJson.getString("access_token");
        }

        // 第四步：拉取用户信息(需scope为 snsapi_userinfo)
        String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid
                + "&lang=zh_CN";
        log.info("获取用户信息，getUserInfoUrl=" + getUserInfoUrl.toString());
        JSONObject getUserInfoJson = CommonWechatUtil.doGetJson(getUserInfoUrl);
        /*
         * { "openid":" OPENID", " nickname": NICKNAME, "sex":"1", "province":"PROVINCE"
         * "city":"CITY", "country":"COUNTRY", "headimgurl":
         * "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
         * "privilege":[ "PRIVILEGE1" "PRIVILEGE2" ], "unionid":
         * "o6_bmasdasdsad6_2sgVt7hMZOPfL" }
         */
        log.info("获取用户信息，getUserInfoJson=" + getUserInfoJson.toString());

        openid = getUserInfoJson.getString("openid");
        String nickname = getUserInfoJson.getString("nickname");
        String country = getUserInfoJson.getString("country");
        String province = getUserInfoJson.getString("province");
        String city = getUserInfoJson.getString("city");
        String sex = getUserInfoJson.getString("sex");
        String headimgurl = getUserInfoJson.getString("headimgurl");
        Map<String,Object> map=new HashMap<>();
        map.put("openid",openid);
        map.put("sts",0);
        List<MemberInfo> memberList = memberInfoService.selectByMap(map);
        if (memberList.size() == 0) {

            MemberInfo memberInfo = new MemberInfo();

            memberInfo.setOpenid(openid);


            memberInfo.setSts(0);

            memberInfoService.insert(memberInfo);
        }

        //校验账号是否登录
//        String getToken = (String) RedisUtil.getHashObjectId(Constant.KEY_TOKEN_LOGIN_ID, openid);
//        if (!StringUtils.isBlank(getToken)) {
//            RedisUtil.removeHashObject(Constant.KEY_TOKEN_LOGIN, getToken);
//        }
//
//        String token = ApiUtil.generateToken();
//        RedisUtil.addHashObject(Constant.KEY_TOKEN_LOGIN, token, member);
//        RedisUtil.addHashObjectId(Constant.KEY_TOKEN_LOGIN_ID, openid, token);
//        SessionMember.reflesh(member);

        /*
         * end 获取微信用户基本信息0
         */

        String resSendUrl = request.getContextPath() + "/api/index.html";
        response.sendRedirect(resSendUrl);
    }
    @RequestMapping(value = "/callBack", method = RequestMethod.GET)
    @ResponseBody
    public String callBack() throws Exception {

        String code = request.getParameter("code");
        // 第二步：通过code换取网页授权access_token
        String getTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + ConstantWeChat.APPID + "&secret="
                + ConstantWeChat.APPSECRET + "&code=" + code + "&grant_type=authorization_code";

        log.info("获取token,getTokenUrl=" + getTokenUrl);
        JSONObject getTokenJson = CommonWechatUtil.doGetJson(getTokenUrl);
        /*
         * { "access_token":"ACCESS_TOKEN", "expires_in":7200,
         * "refresh_token":"REFRESH_TOKEN", "openid":"OPENID", "scope":"SCOPE" }
         */
        log.info("获取token,getTokenJson=" + getTokenJson.toJSONString());

        String openid = getTokenJson.getString("openid");
        String access_token = getTokenJson.getString("access_token");
        String refresh_token = getTokenJson.getString("refresh_token");

        // 第五步验证access_token是否失效；展示都不需要
        String vlidTokenUrl = "https://api.weixin.qq.com/sns/auth?access_token=" + access_token + "&openid=" + openid;
        log.info("验证token,vlidTokenUrl=" + vlidTokenUrl);
        JSONObject validTokenJson = CommonWechatUtil.doGetJson(vlidTokenUrl);
        log.info("验证token,validTokenJson=" + validTokenJson.toJSONString());
        if (!"0".equals(validTokenJson.getString("errcode"))) {
            // 第三步：刷新access_token（如果需要）-----暂时没有使用,参考文档https://mp.weixin.qq.com/wiki，
            String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + openid
                    + "&grant_type=refresh_token&refresh_token=" + refresh_token;
            log.info("刷新token,refreshTokenUrl=" + refreshTokenUrl);
            JSONObject refreshTokenJson = CommonWechatUtil.doGetJson(refreshTokenUrl);
            /*
             * { "access_token":"ACCESS_TOKEN", "expires_in":7200,
             * "refresh_token":"REFRESH_TOKEN", "openid":"OPENID", "scope":"SCOPE" }
             */
            log.info("刷新token,refreshTokenJson=" + refreshTokenJson.toJSONString());
            access_token = refreshTokenJson.getString("access_token");
        }

        // 第四步：拉取用户信息(需scope为 snsapi_userinfo)
        String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid
                + "&lang=zh_CN";
        log.info("获取用户信息，getUserInfoUrl=" + getUserInfoUrl.toString());
        JSONObject getUserInfoJson = CommonWechatUtil.doGetJson(getUserInfoUrl);
        /*
         * { "openid":" OPENID", " nickname": NICKNAME, "sex":"1", "province":"PROVINCE"
         * "city":"CITY", "country":"COUNTRY", "headimgurl":
         * "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
         * "privilege":[ "PRIVILEGE1" "PRIVILEGE2" ], "unionid":
         * "o6_bmasdasdsad6_2sgVt7hMZOPfL" }
         */
        log.info("获取用户信息，getUserInfoJson=" + getUserInfoJson.toString());
        /*
         * end 获取微信用户基本信息
         */
        // 获取到用户信息后就可以进行重定向，走自己的业务逻辑了。。。。。。
        // 接来的逻辑就是你系统逻辑了，请自由发挥

        return getUserInfoJson.toString();
    }
}
