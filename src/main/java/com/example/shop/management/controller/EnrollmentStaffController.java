package com.example.shop.management.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.example.shop.base.SessionVehicle;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.*;
import com.example.shop.pub.bean.AttachFile;
import com.example.shop.pub.service.EnrollmentStaffService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动报名人员 前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Api(description = "前台报名活动的控制接口")
@RestController
@RequestMapping("/memberInfo/enrollmentStaff")
public class EnrollmentStaffController {
    @Autowired
    private EnrollmentStaffService enrollmentStaffService;
    @ApiOperation(value = "活动报名", notes = "活动报名", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),
            @ApiImplicitParam(name = "activityId", value = "活动ID", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "activityId", value = "活动ID", paramType = "query", required = true, dataType = "string" ),

    })
    @RequestMapping(path = "/addenrollmentStaff", method = {RequestMethod.POST})
    public String addenrollmentStaff(EnrollmentStaff enrollmentStaff) throws Exception {
        if(StringUtils.isBlank(enrollmentStaff.getActivityId())){
            return Result.Result(RC.Enrollment_activityId_ISNOT);
        }
        String memberId = SessionVehicle.get(SessionVehicle.MEMBER_ID);
        enrollmentStaff.setMemberId(memberId);
        enrollmentStaff.setStatus(0);
        enrollmentStaff.setSts(0);
        enrollmentStaff.setCreationTime(new Date());

        enrollmentStaffService.insert(enrollmentStaff);
        // 转存文件到指定的路径
        return Result.Result(RC.SUCCESS);


    }

    @ApiOperation(value = "我的活动报名", notes = "我的活动报名活动报名", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),
            @ApiImplicitParam(name = "pageNum", value = "当前多少页", paramType = "query", required = true, dataType = "int" ),
            @ApiImplicitParam(name = "connt", value = "当前页数量", paramType = "query", required = true, dataType = "int" ),
    })
    @RequestMapping(path = "/MyenrollmentStaffpage", method = {RequestMethod.POST})
    public String MyenrollmentStaffpage(EnrollmentStaff enrollmentStaff,Integer  pageNum,Integer connt) throws Exception {
        PageHelper.startPage(pageNum,connt);
        List<Activity> users=enrollmentStaffService.MyenrollmentStaffpage(enrollmentStaff);
        PageInfo<Activity> pageInfo = new PageInfo<Activity>(users);
        // 转存文件到指定的路径
        return Result.Result(RC.SUCCESS);


    }
    @ApiOperation(value = "取消活动报名", notes = "取消报名", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),
            @ApiImplicitParam(name = "activityId", value = "活动ID", paramType = "query", required = true, dataType = "string" ),

    })
    @RequestMapping(path = "/CancellationRegistration", method = {RequestMethod.POST})
    public String CancellationRegistration(EnrollmentStaff enrollmentStaff) throws Exception {
        enrollmentStaff.setStatus(3);
        enrollmentStaffService.updateById(enrollmentStaff);

        // 转存文件到指定的路径
        return Result.Result(RC.SUCCESS);
    }
}

