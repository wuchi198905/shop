package com.example.shop.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Activity;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.bean.AttachFile;
import com.example.shop.pub.service.ActivityService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动表 后端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Api(description = "后台活动管理")
@RestController
@RequestMapping("/system/activity")
public class ActivitySystemController {
    @Autowired
    private ActivityService activityService;
    @ApiOperation(value = "添加活动", notes = "添加活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "startingTime", value = "开始时间", paramType = "query", required = true, dataType = "date" ),
            @ApiImplicitParam(name = "endTime", value = "活动结束时间", paramType = "query", required = true, dataType = "date" ),
            @ApiImplicitParam(name = "type", value = "活动类型 0：免费活动  1 相亲", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "activityArea", value = "活动地区", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "activeStatus", value = "活动状态  0 未开始  1 开始报名  2  报名截止  3 活动结束", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "activities", value = "活动内容   富文本格式", paramType = "query", required = true, dataType = "string" )
    })
    @ResponseBody
    @RequestMapping(path = "/addActivity", method = {RequestMethod.POST})
    public String MenuPagination(Activity activity) {
                activity.setSts(0);
                activity.setCreationTime(new Date());
                activityService.insert(activity);

        return Result.Result(RC.SUCCESS);
    }

    @ApiOperation(value = "修改活动", notes = "修改活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "startingTime", value = "开始时间", paramType = "query", required = true, dataType = "date" ),
            @ApiImplicitParam(name = "endTime", value = "活动结束时间", paramType = "query", required = true, dataType = "date" ),
            @ApiImplicitParam(name = "type", value = "活动类型 0：免费活动  1 相亲", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "activityArea", value = "活动地区", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "activeStatus", value = "活动状态  0 未开始  1 开始报名  2  报名截止  3 活动结束", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "activities", value = "活动内容   富文本格式", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "activityId", value = "主键", paramType = "query", required = true, dataType = "string" )
    })
    @ResponseBody
    @RequestMapping(path = "/upDateActivity", method = {RequestMethod.POST})
    public String upDateActivity(Activity activity) {
        activity.setSts(0);
        activity.setUpdateTime(new Date());
        activityService.updateById(activity);

        return Result.Result(RC.SUCCESS);
    }
    @ApiOperation(value = "删除活动", notes = "删除活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),

            @ApiImplicitParam(name = "activityId", value = "主键", paramType = "query", required = true, dataType = "string" )
    })
    @ResponseBody
    @RequestMapping(path = "/delectActivity", method = {RequestMethod.POST})
    public String delectActivity(Activity activity) {
        activity.setSts(1);
        activity.setUpdateTime(new Date());
        activityService.updateById(activity);

        return Result.Result(RC.SUCCESS);
    }

    @ApiOperation(value = "分页查看活动", notes = "查看全部图片分页")
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
            Page<Activity> page=new Page<>(connt,pageNum);
            Page page1=activityService.selectMapsPage(page,new EntityWrapper<Activity>().eq("type",type).eq("sts","0"));


            return Result.Result(RC.SUCCESS, page1);
        } catch (Exception e) {
            return Result.Result(RC.system_erry);
        }
    }
}

