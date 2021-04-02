package com.example.shop.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.example.shop.base.connt.DataFileUtil;
import com.example.shop.base.json.ApiUtil;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Carousel;
import com.example.shop.management.bean.LoginUser;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.Utils.ImageType;
import com.example.shop.pub.bean.AttachFile;
import com.example.shop.pub.service.AttachFileService;
import com.example.shop.pub.service.CarouselService;
import com.example.shop.system.bean.UserInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
//            @ApiImplicitParam(name = "path", value = "图片路径", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "jumpLink", value = "图片跳转的网页链接", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "jumpedText", value = "图片跳转文本内容", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "order", value = "顺序", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "type", value = "类型  A网页 ，B文本", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "fileId", value = "上传图片后获取的fileId", paramType = "query", required = true, dataType = "string" )
    })
    @ResponseBody
    @RequestMapping(path = "/addcarousel", method = {RequestMethod.POST})
    public String addcarousel(Carousel carousel,String  fileId) {
        AttachFile file1=attachFileService.selectOne(new EntityWrapper<AttachFile>().eq("file_id",fileId));
        carousel.setSts(0);
        carousel.setPath(file1.getSaveName());
        carouselService.insert(carousel);
        return Result.Result(RC.SUCCESS);
    }
    @ApiOperation(value = "修改轮播图", notes = "修改轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
           @ApiImplicitParam(name = "carouselId", value = "主键", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "jumpLink", value = "图片跳转的网页链接", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "jumpedText", value = "图片跳转文本内容", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "order", value = "顺序", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "type", value = "类型  A网页 ，B文本", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "fileId", value = "上传图片后获取的fileId", paramType = "query", required = true, dataType = "string" )
    })
    @ResponseBody
    @RequestMapping(path = "/updatecarousel", method = {RequestMethod.POST})
    public String updatecarousel(Carousel carousel,String  fileId) {
        AttachFile file1=attachFileService.selectOne(new EntityWrapper<AttachFile>().eq("file_id",fileId));
        carousel.setSts(0);
        carousel.setPath(file1.getSaveName());
        carouselService.updateById(carousel);
        return Result.Result(RC.SUCCESS);
    }
    @ApiOperation(value = "轮播图分页", notes = "轮播图分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "carouselId", value = "主键", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "jumpLink", value = "图片跳转的网页链接", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "jumpedText", value = "图片跳转文本内容", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "order", value = "顺序", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "type", value = "类型  A网页 ，B文本", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "pageNum", value = "当前多少页", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "connt", value = "当前页数量", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/getecarouselpage", method = {RequestMethod.POST})
    public String getecarouselpage(Carousel carousel,int  pageNum,int connt) {

        Page<Carousel>page=new Page<>(pageNum,connt);
        Page page1=carouselService.selectMapsPage(page,null);
//new EntityWrapper<Carousel>().eq("type",carousel.getType()
        return Result.Result(RC.SUCCESS,page1);
    }


    /**
     * 上传文件
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/uploadImage", method = {RequestMethod.POST})
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // throwAppException(!SessionMember.isLogined(), RC.OTHER_TOKEN_TIMEOUT);

            String fileName = file.getName();
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            if(!(ImageType.IMG_TYPE_DMG.equals(suffix.toUpperCase()) ||
                    ImageType.IMG_TYPE_GIF.equals(suffix.toUpperCase()) ||
                    ImageType.IMG_TYPE_JPEG.equals(suffix.toUpperCase()) ||
                    ImageType.IMG_TYPE_JPG.equals(suffix.toUpperCase()) ||
                    ImageType.IMG_TYPE_PNG.equals(suffix.toUpperCase()) ||
                    ImageType.IMG_TYPE_SVG.equals(suffix.toUpperCase()))){
                return Result.Result(RC.FILE_TYPE_Ereey);
            }
            String fileId = DataFileUtil.saveDBImage(file);

            AttachFile file1=attachFileService.selectOne(new EntityWrapper<AttachFile>().eq("file_id",fileId));
            return Result.Result(RC.SUCCESS, file1);
        } catch (Exception e) {
            return Result.Result("40001","上传文件出错", e);
        }
    }
    @ApiOperation(value = "删除轮播图", notes = "删除轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "carouselId", value = "主键", paramType = "query", required = true, dataType = "string" ),
//            @ApiImplicitParam(name = "jumpLink", value = "图片跳转的网页链接", paramType = "query", required = true, dataType = "string" ),
//            @ApiImplicitParam(name = "jumpedText", value = "图片跳转文本内容", paramType = "query", required = true, dataType = "string" ),
//            @ApiImplicitParam(name = "order", value = "顺序", paramType = "query", required = true, dataType = "string" ),
//            @ApiImplicitParam(name = "type", value = "类型  A网页 ，B文本", paramType = "query", required = true, dataType = "string" ),
//            @ApiImplicitParam(name = "pageNum", value = "当前多少页", paramType = "query", required = true, dataType = "string" ),
//            @ApiImplicitParam(name = "connt", value = "当前页数量", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/delectcarousel", method = {RequestMethod.POST})
    public String delectcarousel(Carousel carousel) {
        carouselService.deleteById(carousel);
//        carousel.setSts(0);
//        carousel.setPath(file1.getSaveName());
//        carouselService.updateById(carousel);
        return Result.Result(RC.SUCCESS);
    }
}

