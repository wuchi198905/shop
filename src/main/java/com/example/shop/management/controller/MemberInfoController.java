package com.example.shop.management.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;

import com.example.shop.base.SessionVehicle;
import com.example.shop.base.json.ApiUtil;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.DTO.MemberInfoDTO;
import com.example.shop.management.bean.LoginUser;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.Utils.RedisUtils;
import com.example.shop.pub.service.IMailService;
import com.example.shop.pub.service.MemberInfoService;
import com.example.shop.pub.Utils.IDUtils;
import com.example.shop.pub.Utils.VerifyImageUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.springframework.ui.Model;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Slf4j
@Api(description = "会员前台控制")
@RestController
@RequestMapping("/memberInfo")
public class MemberInfoController {
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private IMailService iMailService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 注册会员
     */
    @ApiOperation(value = "注册", notes = "注册账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "account", value = "手机号", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "code", value = "验证码", paramType = "query", required = true, dataType = "string")})


    @RequestMapping(value = "registered", method = RequestMethod.POST)
    @ResponseBody
    public String registered(@RequestParam(name = "userName") String userName, @RequestParam(name = "account") String account, @RequestParam(name = "password") String password, @RequestParam(name = "code") String code) {


        return memberInfoService.registered(account, userName, password, code);
    }

    /**
     * 注册会员
     */
    @ApiOperation(value = "获取手机验证码", notes = "通过手机号获取验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "手机号", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "code", value = "图形验证码", paramType = "query", required = true, dataType = "string")})


    @RequestMapping(value = "getVerificationCode", method = RequestMethod.POST)
    @ResponseBody
    public String getVerificationCode(@RequestParam(name = "account") String account, @RequestParam(name = "code") String code) {
        int mun = memberInfoService.selectCount(new EntityWrapper<MemberInfo>().eq("account", account));
        if (mun > 0) {
            return Result.Result(RC.REGIST_PARAM_MOBILE_OCCUPIED);
        }
        boolean fig = memberInfoService.getVerificationCode(account);

        if (fig) {
            return Result.Result("00000", "获取验证码成功");
        }
        return Result.Result(RC.REGIST_PARAM_TYPE_INVALID);
    }

    // 保存横轴位置用于对比，并设置最大数量为10000，多了就先进先出，并设置超时时间为70秒
    public static Cache<String, Integer> cacheg = CacheBuilder.newBuilder().expireAfterWrite(70, TimeUnit.SECONDS)
            .maximumSize(10000).build();

    @GetMapping
    @ApiOperation(value = "获取图形验证码", notes = "获取图形验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "手机号", paramType = "query", required = true, dataType = "string"),
    })
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String test(HttpServletRequest request, Model model) throws IOException {
        return "index.html";
    }

    @ApiOperation(value = "获取图形验证码", notes = "获取图形验证码")
    @GetMapping
    @ApiImplicitParams({
            //@ApiImplicitParam(name = "account", value = "手机号", paramType = "query", required = true, dataType = "string" ),
    })
    @RequestMapping(value = "getPic", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getPic(HttpServletRequest request) throws IOException {
        // 读取图库目录
        File imgCatalog = new File(ResourceUtils.getURL("classpath:").getPath() + "sliderimage\\targets\\");
        File[] files = imgCatalog.listFiles();
        // 随机选择需要切的图
        int randNum = new Random().nextInt(files.length);
        File targetFile = files[randNum];
        // 随机选择剪切模版
        Random r = new Random();
        int num = r.nextInt(6) + 1;
        File tempImgFile = new File(ResourceUtils.getURL("classpath:").getPath() + "sliderimage\\templates\\" + num
                + "-w.png");
        // 根据模板裁剪图片
        try {
            Map<String, Object> resultMap = VerifyImageUtil.pictureTemplatesCut(tempImgFile, targetFile);
            // 生成流水号，这里就使用时间戳代替
            String lno = Calendar.getInstance().getTimeInMillis() + "";
            cacheg.put(lno, Integer.valueOf(resultMap.get("xWidth") + ""));
            resultMap.put("capcode", lno);
            // 移除横坐标送前端
            resultMap.remove("xWidth");
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "获取图形验证码", notes = "获取图形验证码")
    @GetMapping
    @ApiImplicitParams({
            // @ApiImplicitParam(name = "account", value = "手机号", paramType = "query", required = true, dataType = "string" ),
    })
    @RequestMapping(value = "checkcapcode", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> checkcapcode(@RequestParam("xpos") int xpos,
                                     @RequestParam("capcode") String capcode, HttpServletRequest request) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        Integer x = cacheg.getIfPresent(capcode);
        if (x == null) {
            // 超期
            result.put("code", 99998);
        } else if (xpos - x > 5 || xpos - x < -5) {
            // 验证失败
            result.put("code", 99999);
        } else {
            // 验证成功
            result.put("code", 00000);
        }

        return result;
    }

    /**
     * 完善信息
     */
    @ApiOperation(value = "完善信息", notes = "完善会员的基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),
            @ApiImplicitParam(name = "memberName", value = "姓名", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "memberGender", value = "性别  ：男：A  女：B", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "memberAge", value = "年龄", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "memberEducation", value = "学历", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "memberWeight", value = "体重", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "maritalStatus", value = "0  未婚 1已婚 2离异 3丧偶 ", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "contactNumber", value = "联系电话", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "mailbox", value = "邮箱", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "province", value = "省", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "city", value = "市", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "area", value = "区", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "address", value = "详细地址", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "idNumber", value = "身份证编号", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "weChatNumber", value = "微信号", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "birthday", value = "生日", paramType = "query", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "constellation", value = "星座", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "memberHeight", value = "身高", paramType = "query", required = true, dataType = "string")
    })

    @RequestMapping(value = "/perfectInformation", method = RequestMethod.POST)
    @ResponseBody
    public String perfectInformation(MemberInfo memberInfo) {
        if (StringUtils.isBlank(memberInfo.getAddress())) {
            return Result.Result(RC.PERFCT_ADDRESS_ISNOTNULL);
        }
        if (StringUtils.isBlank(memberInfo.getMemberName())) {
            return Result.Result(RC.PERFCT_MEMBERNAME_ISNOTNULL);
        }
        if (StringUtils.isBlank(memberInfo.getMemberGender())) {
            return Result.Result(RC.PERFCT_MEMBERGRNDER_ISNOTNULL);
        }
        if (memberInfo.getMemberAge() == null) {
            return Result.Result(RC.PERFCT_MEBERAGE_ISNOTNULL);
        }
        if (StringUtils.isBlank(memberInfo.getMemberEducation())) {
            return Result.Result(RC.PERFCT_MEMBEREUCATTION_ISNOTNULL);
        }
        if (StringUtils.isBlank(memberInfo.getMemberEducation())) {
            return Result.Result(RC.PERFCT_MEMBEREUCATTION_ISNOTNULL);
        }
        if (StringUtils.isBlank(memberInfo.getProvince())) {
            return Result.Result(RC.PERFCT_PROVINCE_ISNOTNULL);
        }
        if (StringUtils.isBlank(memberInfo.getMaritalStatus())) {
            return Result.Result(RC.PERFCT_MARITALsTATUS_ISNOTNULL);
        }
        if (StringUtils.isBlank(memberInfo.getCity())) {
            return Result.Result(RC.PERFCT_CITY_ISNOTNULL);
        }
        if (!IDUtils.isIDNumber(memberInfo.getIdNumber())) {
            return Result.Result(RC.PERFCT_IDNUMBER_ISNOTNULL);
        }
        String VHEICLEiD = SessionVehicle.get(SessionVehicle.MEMBER_ID);
        memberInfo.setMemberId(Integer.valueOf(VHEICLEiD));
        memberInfo.setRealNameAuthenticationStatus("1");
        memberInfoService.updateById(memberInfo);
        memberInfo = memberInfoService.selectById(memberInfo);
        memberInfo.setPassword(null);
        return Result.Result("00000", "操作成功", memberInfo);
    }

    @ApiOperation(value = "登录", notes = "通过手机号注册账号")

    @ApiImplicitParams({

            @ApiImplicitParam(name = "account", value = "手机号", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true, dataType = "string")
    })

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam(name = "account") String account, @RequestParam(name = "password") String password) {
        log.warn("执行登录操作!");
        //先执行登录验证的过滤操作,才会执行后面这些乱七八糟的异常
        //throw new MyException("测试自定义异常!");
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setPassword(password);
        memberInfo.setAccount(account);
        Map<String, Object> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        //throw  new MyException("我的模拟业务代码的异常!");
        MemberInfo user1 = null;
        try {
            user1 = memberInfoService.selectByMap(map).get(0);
            user1.setLastLoginTime(new Date());
            memberInfoService.updateById(user1);
        } catch (Exception e) {
            //throw new MyException("100002","empty","/API/getUserName","登录失败,用户密码错误");
            return Result.Result("100002", "登录失败,用户密码错误");
        }
        LoginUser loginUser = memberInfoService.login(user1);
        Map<String, String> map2 = new HashMap<>();
        map2.put("token", loginUser.getToken());
        map2.put("username", user1.getUserName());
        map2.put("Status", user1.getRealNameAuthenticationStatus());
        if (loginUser != null) {
            return Result.Result("00000", "登录成功", map2);
        }
        return Result.Result("100002", "登录失败");
    }

    /***
     * 上传会员登录时的最新位置
     * @param currentEconomy 经度
     * @param currentDimension 纬度
     * @return
     */

    @ApiOperation(value = "上传会员登录时的最新位置", notes = "上传会员登录时的最新位置")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "currentEconomy", value = "经度", paramType = "query", required = true, dataType = "string"),

            @ApiImplicitParam(name = "currentDimension", value = "纬度", paramType = "query", required = true, dataType = "string")
    })
    @RequestMapping(value = "/UploadCurrentLocation", method = RequestMethod.POST)
    @ResponseBody
    public String UploadCurrentLocation(@RequestParam(name = "currentEconomy") String currentEconomy, @RequestParam(name = "currentDimension") String currentDimension) {
        String memberId = SessionVehicle.get(SessionVehicle.MEMBER_ID);
        // log.warn("执行登录操作!");
        //先执行登录验证的过滤操作,才会执行后面这些乱七八糟的异常
        //throw new MyException("测试自定义异常!");
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(Integer.valueOf(memberId));
        memberInfo.setCurrentEconomy(currentEconomy);
        memberInfo.setConstellation(currentDimension);
        boolean fig = memberInfoService.updateById(memberInfo);


        if (fig) {
            return Result.Result("00000", "上传当前位置成功");
        }
        return Result.Result("100002", "上传当前位置失败");
    }

    /**
     * 会员列表
     */
    @ApiOperation(value = "首页展示的会员列表", notes = "首页展示的会员类表")
    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true)
    })
    @RequestMapping(value = "/HomepageDisplayPagination", method = RequestMethod.POST)
    @ResponseBody
    public String HomepageDisplayPagination() {
        //PageHelper.startPage(pageNum,5);
        List<MemberInfoDTO> list = memberInfoService.HomepageDisplayPagination();


        return Result.Result(RC.SUCCESS, list);
    }

    /**
     * 最新注册会员
     */
    @ApiOperation(value = "最新注册会员", notes = "最新注册会员")
    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true)
    })
    @RequestMapping(value = "Latestregisteredmembers", method = RequestMethod.POST)
    @ResponseBody
    public String Latestregisteredmembers() {
        //PageHelper.startPage(pageNum,5);
        List<MemberInfoDTO> list = memberInfoService.Latestregisteredmembers();


        return Result.Result(RC.SUCCESS, list);
    }

    /**
     * 发送邮箱验证码
     */
    @ApiOperation(value = "发送邮箱验证码", notes = "发送邮箱验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "邮箱", paramType = "query", required = true, dataType = "string"),
    })


    @RequestMapping(value = "getVerificationMailCode", method = RequestMethod.POST)
    @ResponseBody
    public String getVerificationMailCode(@RequestParam(name = "account") String account) {
        int mun = memberInfoService.selectCount(new EntityWrapper<MemberInfo>().eq("account", account));
        if (mun > 0) {
            return Result.Result(RC.REGIST_PARAM_MOBILE_OCCUPIED);
        }
        String code = ApiUtil.generateSmsCode();
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setAccount(account);

        log.info("验证犸是   " + code);
        iMailService.sendSimpleMail(account, "验证码", "注册验证码是" + code + "，请不要告诉其他人");
        redisUtils.set(account, code, 60L);
        boolean fig = memberInfoService.getVerificationCode(account);

        if (fig) {
            return Result.Result("00000", "获取验证码成功");
        }
        return Result.Result(RC.REGIST_PARAM_TYPE_INVALID);
    }


    /**
     * 短信验证码登录
     */
    @ApiOperation(value = "短信验证码登录", notes = "短信验证码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "手机号", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "code", value = "验证码", paramType = "query", required = true, dataType = "string")})


    @RequestMapping(value = "coderegistered", method = RequestMethod.POST)
    @ResponseBody
    public String coderegistered(@RequestParam(name = "account") String account, @RequestParam(name = "code") String code) {


        return memberInfoService.coderegistered(account, code);
    }

    /**
     * 查询用户名是否存在
     */
    @ApiOperation(value = "查询用户名是否存在", notes = "查询用户名是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "query", required = true, dataType = "string"),

    })

    @RequestMapping(value = "qeeruserName", method = RequestMethod.POST)
    @ResponseBody
    public String qeeruserName(String userName) {
        int mun = memberInfoService.selectCount(new EntityWrapper<MemberInfo>().eq("user_name", userName));
        if (mun > 0) {
            return Result.Result(RC.REGIST_PARAM_MOBILE_OCCUPIED);
        }
        return Result.Result(RC.SUCCESS);
    }
    /**
     * 查询用户名是否存在
     */
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),

    })

    @RequestMapping(value = "loginOut", method = RequestMethod.POST)
    @ResponseBody
    public String loginOut(String userName) {

        return Result.Result(RC.SUCCESS);
    }
}

