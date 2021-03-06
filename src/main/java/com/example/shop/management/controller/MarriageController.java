package com.example.shop.management.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.shop.base.SessionVehicle;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Carousel;
import com.example.shop.management.bean.Image;
import com.example.shop.management.bean.Marriage;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.service.ImageService;
import com.example.shop.pub.service.MarriageService;
import com.example.shop.pub.service.MemberInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 * 征婚信息表 前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@RestController
@RequestMapping("/memberInfo/marriage")
public class MarriageController {
    @Autowired
    private MarriageService advisoryService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "添加征婚", notes = "添加征婚", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),

            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "ages", value = "年龄", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "area", value = "地区", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "weight", value = "体重", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "remarks", value = "描述", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "remarks", value = "描述", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "effectiveTime", value = "有效时间 ", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/addArouselpage", method = {RequestMethod.POST})
    public String addArouselpage(Marriage carousel) throws ParseException {

        String memberId = SessionVehicle.get(SessionVehicle.MEMBER_ID);
        Image image = imageService.selectOne(new EntityWrapper<Image>().eq("member_id", memberId).eq("type", "A"));
        carousel.setPath(image.getPath());
        carousel.setStatus("0");
        carousel.setMemberId(Integer.valueOf(memberId));
        MemberInfo memberInfo=new MemberInfo();
        memberInfo.setMemberId(Integer.valueOf(memberId));
        memberInfo=memberInfoService.selectById(memberInfo);
        carousel.setHeights(memberInfo.getMemberHeight());
        carousel.setAges(memberInfo.getMemberAge().toString());
        carousel.setArea(memberInfo.getArea());
        carousel.setCreationTime(new Date());

        boolean flag = false;
        Date nowDate = new Date();
        Date pastDate = null;
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");
        pastDate = sdf.parse(carousel.getEffectiveTime());
        //调用Date里面的before方法来做判断
        flag = pastDate.before(nowDate);
        if (flag) {
            return Result.Result(RC.marriage_time);
        }
        advisoryService.insert(carousel);

        return Result.Result(RC.SUCCESS);
    }


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
       // Page page1=advisoryService.selectMapsPage(page,new EntityWrapper<Marriage>().like("title",carousel.getTitle()).eq("status","2"));

        Page page1=advisoryService.selectMapsPage(page,new EntityWrapper<Marriage>());



        return Result.Result(RC.SUCCESS,page1);
    }
}

