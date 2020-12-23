package com.example.shop.system.controller;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.DTO.ImageDTO;
import com.example.shop.management.bean.Image;
import com.example.shop.pub.service.ImageService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(description = "图片审核列表")
@RestController
@RequestMapping("/system/image")
public class ImageSystemController {
    @Autowired
    private ImageService imageService;
    /**
     *
     */
    @ApiOperation(value = "会员图片列表分页", notes = "会员图片列表分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", paramType = "query", required = true, dataType = "String" ),
    })
    @ResponseBody
    @RequestMapping(path = "/imagePagination", method = {RequestMethod.POST})
    public String imagePagination(ImageDTO images, @PathVariable("pageNum") int pageNum) {

        PageHelper.startPage(pageNum,10);
        List<ImageDTO> list=imageService.imagePagination(images);
        PageInfo<ImageDTO> pageInfo = new PageInfo<ImageDTO>(list);
        return pageInfo.toString();
    }
    @ApiOperation(value = "会员图片审核", notes = "会员图片列表分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "imageId", value = "主键id", paramType = "query", required = true, dataType = "String" ),
            @ApiImplicitParam(name = "status", value = "状态  1  审核通过  0未审核  2  审核未通过", paramType = "query", required = true, dataType = "String" ),
    })
    @ResponseBody
    @RequestMapping(path = "/imageExamine", method = {RequestMethod.POST})
    public String imageExamine(Image images) {

        imageService.updateById(images);
        return Result.Result(RC.SUCCESS);
    }
}
