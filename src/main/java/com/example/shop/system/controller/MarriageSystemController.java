package com.example.shop.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Carousel;
import com.example.shop.management.bean.Marriage;
import com.example.shop.pub.service.MarriageService;
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
 * 征婚信息表 前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Api(description = "征婚信息表 前端控制器")
@RestController
@RequestMapping("/system/marriage")
public class MarriageSystemController {
    @Autowired
    private MarriageService advisoryService;

    @ApiOperation(value = "征婚列表", notes = "征婚列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),

            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "ages", value = "年龄", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "area", value = "地区", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "weight", value = "体重", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "remarks", value = "描述", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "status", value = "0 待审核 1已审核 3已失效 ", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/getecarouselpage", method = {RequestMethod.POST})
    public String getecarouselpage(Marriage carousel, Integer  pageNum, Integer connt) {

        Page<Carousel> page=new Page<>(pageNum,connt);
        Page page1=advisoryService.selectMapsPage(page,new EntityWrapper<Marriage>().like("title",carousel.getTitle()).eq("status","2"));

        return Result.Result(RC.SUCCESS,page1);
    }
    /**
     *
     */
    @ApiOperation(value = "征婚审核", notes = "征婚审核", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),

            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", required = true, dataType = "string" ),
           @ApiImplicitParam(name = "marriageId", value = "主键", paramType = "query", required = true, dataType = "string" ),
//            @ApiImplicitParam(name = "area", value = "地区", paramType = "query", required = true, dataType = "string" ),
//            @ApiImplicitParam(name = "weight", value = "体重", paramType = "query", required = true, dataType = "string" ),
//            @ApiImplicitParam(name = "remarks", value = "描述", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "status", value = "0 待审核 1已审核 3已失效 ", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/updateecarouselpage", method = {RequestMethod.POST})
    public String updateecarouselpage(Marriage carousel) {


        advisoryService.updateById(carousel);

        return Result.Result(RC.SUCCESS);
    }
}

