package com.example.shop.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.example.shop.base.SessionVehicle;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;

import com.example.shop.management.bean.MemberInfo;

import com.example.shop.pub.Utils.IDUtils;
import com.example.shop.pub.service.MemberInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Slf4j
@Api(description = "会员后台控制")
@RestController
@RequestMapping("/system/memberInfo")
public class MemberInfoSystemController {
    @Autowired
    private MemberInfoService memberInfoService;

    @ApiOperation(value = "查询菜单列表", notes = "查询菜单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/memberInfoPagination", method = {RequestMethod.POST})
    public String MenuPagination(MemberInfo memberInfo, @RequestParam(name = "pageNum")String pageNum) {

//        PageHelper.startPage(Integer.valueOf(pageNum),10);
//        List<MemberInfo> users=memberInfoService.MenuPagination(memberInfo);
//        PageInfo<MemberInfo> pageInfo = new PageInfo<MemberInfo>(users);
        Page<MemberInfo> page=new Page<>(Integer.valueOf(pageNum),10);
        Page page1=memberInfoService.selectMapsPage(page,new EntityWrapper<MemberInfo>().eq("sts","0"));
        return  Result.Result(RC.SUCCESS,page1);
    }

    @ApiOperation(value = "会员详细信息", notes = "会员详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "memberId", value = "会员主键", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/memberInfoSeletOne", method = {RequestMethod.POST})
    public String memberInfoSeletOne(MemberInfo memberInfo) {


            memberInfo=memberInfoService.selectById(memberInfo);
            memberInfo.setPassword("");
         return  Result.Result(RC.SUCCESS,memberInfo);
    }
    /**
     * 完善信息
     */
    @ApiOperation(value = "后台添加会员", notes = "后台添加会员")
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

        memberInfo.setRealNameAuthenticationStatus("1");
        memberInfoService.insert(memberInfo);
        return Result.Result("00000", "操作成功", memberInfo);
    }
}
