package com.example.shop.management.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Activity;
import com.example.shop.pub.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 活动表 前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Api(description = "活动列表")
@RestController
@RequestMapping("/memberInfo/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @ApiOperation(value = "活动列表", notes = "活动列表")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "connt", value = "每页显示的数量", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "pageNum", value = "第几页", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "type", value = "活动类型", paramType = "query", required = true, dataType = "string" ),

    })
    @ResponseBody
    @RequestMapping(path = "/querdImagePage", method = {RequestMethod.POST})
    public String querdImagePage(int connt,int pageNum,String type) {
        try {
            Page<Activity> page=new Page<>(pageNum,connt);
            if(type==null){
                Page page1=activityService.selectMapsPage(page,new EntityWrapper<Activity>().eq("sts","0"));
                System.out.println(page1);
                return Result.Result(RC.SUCCESS, page1);
            }else{
                Page page1=activityService.selectMapsPage(page,new EntityWrapper<Activity>().eq("type",type).eq("sts","0"));
                System.out.println(page1);
                return Result.Result(RC.SUCCESS, page1);
            }




        } catch (Exception e) {
            return Result.Result(RC.system_erry);
        }
    }
    @ApiOperation(value = "活动详情", notes = "活动详情")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "activityId", value = "主键", paramType = "query", required = true, dataType = "string" ),


    })
    @ResponseBody
    @RequestMapping(path = "/querdImageOne", method = {RequestMethod.POST})
    public String querdImageOne(Activity activity) {
        try {


            Activity page1=activityService.selectById(activity);
            return Result.Result(RC.SUCCESS, page1);





        } catch (Exception e) {
            return Result.Result(RC.system_erry);
        }
    }
}

