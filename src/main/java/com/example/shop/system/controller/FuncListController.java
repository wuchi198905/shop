package com.example.shop.system.controller;


import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.example.shop.base.json.ApiUtil;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.DTO.FuncListDTO;
import com.example.shop.management.bean.LoginUser;
import com.example.shop.system.bean.FuncList;
import com.example.shop.system.bean.UserInfo;
import com.example.shop.system.service.FuncListService;
import com.example.shop.system.service.UserInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-22
 */
@Api(description = "菜单控制接口")
@RestController
@RequestMapping("/system/funcList")
public class FuncListController {
    @Autowired
    private FuncListService funcListService;
    @ApiOperation(value = "查询菜单列表", notes = "查询菜单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/MenuPagination", method = {RequestMethod.POST})
    public String MenuPagination(FuncList funcList,@PathVariable("pageNum") String pageNum) {

        PageHelper.startPage(Integer.valueOf(pageNum),10);
        List<FuncListDTO> users=funcListService.MenuPagination(funcList);
        PageInfo<FuncListDTO> pageInfo = new PageInfo<FuncListDTO>(users);
        return pageInfo.toString();
    }
    @ApiOperation(value = "新增菜单", notes = "新增菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "parentId", value = "上一级id，没有为空", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "menuTitle", value = "菜单标题", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "menuDesc", value = "菜单描述", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "menuIcon", value = "菜单图片", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "menuUrl", value = "地址", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "menuLevel", value = "菜单级别  1   2   3", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "listOrder", value = "排序", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/addFuncList", method = {RequestMethod.POST})
    public String addFuncList(FuncList funcList) {

        funcList.setSts("A");

       boolean fig=funcListService.insert(funcList);

        return Result.Result(RC.SUCCESS);
    }
    @ApiOperation(value = "修改菜单", notes = "修改菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "funcId", value = "主键id", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "parentId", value = "上一级id，没有为空", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "menuTitle", value = "菜单标题", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "menuDesc", value = "菜单描述", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "menuIcon", value = "菜单图片", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "menuUrl", value = "地址", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "menuLevel", value = "菜单级别  1   2   3", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "listOrder", value = "排序", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/updateFuncList", method = {RequestMethod.POST})
    public String updateFuncList(FuncList funcList) {

        funcList.setSts("A");

        boolean fig=funcListService.updateById(funcList);

        return Result.Result(RC.SUCCESS);
    }
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "funcId", value = "主键id", paramType = "query", required = true, dataType = "string" ),

    })
    @ResponseBody
    @RequestMapping(path = "/delectFuncList", method = {RequestMethod.POST})
    public String delectFuncList(FuncList funcList) {

        //funcList.setSts("P");

        boolean fig=funcListService.deleteById(funcList);

        return Result.Result(RC.SUCCESS);
    }
}

