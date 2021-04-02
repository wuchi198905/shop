package com.example.shop.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.DTO.ImageDTO;
import com.example.shop.management.bean.Image;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.service.ImageService;
import com.example.shop.system.bean.FuncList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/imagePagination", method = {RequestMethod.POST})
    public String imagePagination(ImageDTO images, String pageNum) {

        PageHelper.startPage(Integer.valueOf(pageNum),10);
        images.setSts(0);
        List<ImageDTO> list=imageService.imagePagination(images);
//        PageInfo<ImageDTO> pageInfo = new PageInfo<ImageDTO>(list);
//        return Result.Result(RC.SUCCESS,pageInfo);
        Page<Image> page=new Page<>(Integer.valueOf(pageNum),10);
        Page page1=imageService.selectPage(page,new EntityWrapper<Image>().eq("sts","0").eq("type",images.getType()));
        List<Image> list2=page1.getRecords();
        List<ImageDTO> list3=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list2.size();j++){
                if(list.get(i).getImageId().equals(list2.get(j).getImageId())){
                    list3.add(list.get(i));
                }
            }

        }
        page1.setRecords(list3);
        return  Result.Result(RC.SUCCESS,page1);
    }
    @ApiOperation(value = "会员图片审核", notes = "会员图片列表分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "imageId", value = "主键id", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "status", value = "状态  1  审核通过  0未审核  2  审核未通过", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/imageExamine", method = {RequestMethod.POST})
    public String imageExamine(Image images) {

        imageService.updateById(images);
        return Result.Result(RC.SUCCESS);
    }
}
