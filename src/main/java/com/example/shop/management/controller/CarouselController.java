package com.example.shop.management.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Carousel;
import com.example.shop.pub.service.CarouselService;
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
 * 轮播图 前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Api(description = "前台轮播图控制")
@RestController
@RequestMapping("/memberInfo/carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;
    @ApiOperation(value = "轮播图展示", notes = "轮播图展示")
    @ApiImplicitParams({


    })
    @ResponseBody
    @RequestMapping(path = "/getecarousel", method = {RequestMethod.POST})
    public String getecarousel() {
        Page<Carousel> page=new Page<>(1,4);
        Page page1=carouselService.selectMapsPage(page,new EntityWrapper<Carousel>().orderBy("`order`",true));

        return Result.Result(RC.SUCCESS,page1);
    }
}

