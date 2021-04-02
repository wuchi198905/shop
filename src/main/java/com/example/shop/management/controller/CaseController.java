package com.example.shop.management.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Case;
import com.example.shop.pub.service.CaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 幸福案例 前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Api(description = "前台幸福案例的控制接口")
@RestController
@RequestMapping("/memberInfo/case")
public class CaseController {
    @Autowired
    private CaseService caseService;
    @ApiOperation(value = "查询幸福案例", notes = "查询幸福案例", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),

            @ApiImplicitParam(name = "caseId", value = "主键", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "pageNum", value = "当前多少页", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "connt", value = "当前页数量", paramType = "query", required = true, dataType = "string" ),
    })
    @RequestMapping(path = "/querctteenrollmentStaff", method = {RequestMethod.POST})
    public String querctteenrollmentStaff(Case cases, Integer  pageNum, Integer connt) throws Exception {
        Page<Case> page=new Page<>(connt,pageNum);
        if(cases.getTitle()==null){
            Page page1=caseService.selectMapsPage(page,new EntityWrapper<Case>());
            return Result.Result(RC.SUCCESS,page1);
        }else{
            Page page1=caseService.selectMapsPage(page,new EntityWrapper<Case>().eq("title",cases.getTitle()));
            return Result.Result(RC.SUCCESS,page1);
        }


    }
    @ApiOperation(value = "幸福案例详情", notes = "幸福案例详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),

            @ApiImplicitParam(name = "caseId", value = "主键", paramType = "query", required = true, dataType = "string" ),
    })
    @RequestMapping(path = "/ctteenrollmentStaffInfo", method = {RequestMethod.POST})
    public String ctteenrollmentStaffInfo(Case cases) throws Exception {

        cases=caseService.selectById(cases);
        // 转存文件到指定的路径
        return Result.Result(RC.SUCCESS,cases);


    }
}

