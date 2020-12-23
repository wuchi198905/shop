package com.example.shop.system.controller;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.DTO.ImageDTO;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.service.ImageService;
import com.example.shop.pub.service.MemberInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", paramType = "query", required = true, dataType = "String" ),
    })
    @ResponseBody
    @RequestMapping(path = "/memberInfoPagination", method = {RequestMethod.POST})
    public String MenuPagination(MemberInfo memberInfo, @PathVariable("pageNum") int pageNum) {

        PageHelper.startPage(pageNum,10);
        List<MemberInfo> users=memberInfoService.MenuPagination(memberInfo);
        PageInfo<MemberInfo> pageInfo = new PageInfo<MemberInfo>(users);
        return pageInfo.toString();
    }

    @ApiOperation(value = "会员详细信息", notes = "会员详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "memberId", value = "会员主键", paramType = "query", required = true, dataType = "String" ),
    })
    @ResponseBody
    @RequestMapping(path = "/memberInfoSeletOne", method = {RequestMethod.POST})
    public String memberInfoSeletOne(MemberInfo memberInfo) {


            memberInfo=memberInfoService.selectById(memberInfo);

         return  Result.Result(RC.SUCCESS,memberInfo);
    }

}
