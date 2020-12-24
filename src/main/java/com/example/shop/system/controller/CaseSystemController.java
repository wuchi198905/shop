package com.example.shop.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.shop.base.SessionVehicle;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Carousel;
import com.example.shop.management.bean.Case;
import com.example.shop.management.bean.EnrollmentStaff;
import com.example.shop.pub.service.CaseService;
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

/**
 * <p>
 * 幸福案例 前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Api(description = "后台幸福案例的控制接口")
@RestController
@RequestMapping("/system/case")
public class CaseSystemController {
    @Autowired
    private CaseService caseService;
    @ApiOperation(value = "添加幸福案例", notes = "添加幸福案例", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "content", value = "内容 富文本", paramType = "query", required = true, dataType = "string" ),

    })
    @RequestMapping(path = "/addenrollmentStaff", method = {RequestMethod.POST})
    public String addenrollmentStaff(Case cases) throws Exception {

        String memberId = SessionVehicle.get(SessionVehicle.MEMBER_ID);
        cases.setCreationTime(new Date());

        caseService.insert(cases);
        // 转存文件到指定的路径
        return Result.Result(RC.SUCCESS);


    }
    @ApiOperation(value = "修改幸福案例", notes = "修改幸福案例", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "content", value = "内容 富文本", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "caseId", value = "主键", paramType = "query", required = true, dataType = "string" ),
    })
    @RequestMapping(path = "/uodateenrollmentStaff", method = {RequestMethod.POST})
    public String uodateenrollmentStaff(Case cases) throws Exception {

        String memberId = SessionVehicle.get(SessionVehicle.MEMBER_ID);
        cases.setCreationTime(new Date());

        caseService.updateById(cases);
        // 转存文件到指定的路径
        return Result.Result(RC.SUCCESS);


    }
    @ApiOperation(value = "删除幸福案例", notes = "删除幸福案例", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),

            @ApiImplicitParam(name = "caseId", value = "主键", paramType = "query", required = true, dataType = "string" ),
    })
    @RequestMapping(path = "/delectteenrollmentStaff", method = {RequestMethod.POST})
    public String delectteenrollmentStaff(Case cases) throws Exception {
        cases.setCreationTime(new Date());
        caseService.deleteById(cases);
        // 转存文件到指定的路径
        return Result.Result(RC.SUCCESS);


    }
    @ApiOperation(value = "查询幸福案例", notes = "查询幸福案例", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),

            @ApiImplicitParam(name = "caseId", value = "主键", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "pageNum", value = "当前多少页", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "connt", value = "当前页数量", paramType = "query", required = true, dataType = "string" ),
    })
    @RequestMapping(path = "/querctteenrollmentStaff", method = {RequestMethod.POST})
    public String querctteenrollmentStaff(Case cases,Integer  pageNum,Integer connt) throws Exception {
        Page<Case> page=new Page<>(connt,pageNum);
        Page page1=caseService.selectMapsPage(page,new EntityWrapper<Case>().eq("title",cases.getTitle()));
        // 转存文件到指定的路径
        return Result.Result(RC.SUCCESS,page1);


    }
}

