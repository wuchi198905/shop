package com.example.shop.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.example.shop.base.json.ApiUtil;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Carousel;
import com.example.shop.management.bean.LoginUser;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.bean.AttachFile;
import com.example.shop.pub.service.AttachFileService;
import com.example.shop.pub.service.CarouselService;
import com.example.shop.system.bean.UserInfo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 轮播图 后端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Api(description = "后台轮播图控制")
@RestController
@RequestMapping("/system/carousel")
public class CarouselSystemController {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private AttachFileService attachFileService;
    @ApiOperation(value = "添加轮播图", notes = "添加轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "String" ),
//            @ApiImplicitParam(name = "path", value = "图片路径", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "jumpLink", value = "图片跳转的网页链接", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "jumpedText", value = "图片跳转文本内容", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "order", value = "顺序", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "type", value = "类型  A网页 ，B文本", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "fileId", value = "上传图片后获取的fileId", paramType = "query", required = true, dataType = "String" )
    })
    @ResponseBody
    @RequestMapping(path = "/addcarousel", method = {RequestMethod.POST})
    public String addcarousel(Carousel carousel,String  fileId) {
        AttachFile file1=attachFileService.selectOne(new EntityWrapper<AttachFile>().eq("file_id",fileId));
        carousel.setSts(0);
        carousel.setPath(file1.getSaveName());
        carouselService.insert(carousel);
        return Result.Result(RC.LOGIN_USER_ISNOT);
    }
    @ApiOperation(value = "修改轮播图", notes = "添加轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "String" ),
           @ApiImplicitParam(name = "carouselId", value = "主键", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "jumpLink", value = "图片跳转的网页链接", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "jumpedText", value = "图片跳转文本内容", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "order", value = "顺序", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "type", value = "类型  A网页 ，B文本", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "fileId", value = "上传图片后获取的fileId", paramType = "query", required = true, dataType = "String" )
    })
    @ResponseBody
    @RequestMapping(path = "/updatecarousel", method = {RequestMethod.POST})
    public String updatecarousel(Carousel carousel,String  fileId) {
        AttachFile file1=attachFileService.selectOne(new EntityWrapper<AttachFile>().eq("file_id",fileId));
        carousel.setSts(0);
        carousel.setPath(file1.getSaveName());
        carouselService.updateById(carousel);
        return Result.Result(RC.LOGIN_USER_ISNOT);
    }
    @ApiOperation(value = "修改轮播图", notes = "添加轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "carouselId", value = "主键", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "jumpLink", value = "图片跳转的网页链接", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "jumpedText", value = "图片跳转文本内容", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "order", value = "顺序", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "type", value = "类型  A网页 ，B文本", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "fileId", value = "上传图片后获取的fileId", paramType = "query", required = true, dataType = "String" )
    })
    @ResponseBody
    @RequestMapping(path = "/getecarouselpage", method = {RequestMethod.POST})
    public String getecarouselpage(Carousel carousel,Integer  pageNum,Integer connt) {
        Page<Carousel>page=new Page<>(connt,pageNum);
        Page page1=carouselService.selectMapsPage(page,new EntityWrapper<Carousel>().eq("type",carousel.getType()));

        return Result.Result(RC.SUCCESS,page1);
    }
}

