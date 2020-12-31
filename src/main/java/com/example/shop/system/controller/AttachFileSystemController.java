package com.example.shop.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.shop.base.connt.DataFileUtil;
import com.example.shop.base.json.ApiUtil;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Carousel;
import com.example.shop.pub.Utils.ImageType;
import com.example.shop.pub.bean.AttachFile;
import com.example.shop.pub.service.AttachFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-23
 */
@Api(description = "后台图片管理功能查全部图片，删除图片")
@RestController
@RequestMapping("/system/attachFile")
public class AttachFileSystemController {
    @Autowired
    private AttachFileService attachFileService;

    @ApiOperation(value = "查看全部图片分页", notes = "查看全部图片分页")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "connt", value = "每页显示的数量", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "pageNum", value = "第几页", paramType = "query", required = true, dataType = "string" ),
            @ApiImplicitParam(name = "fileName", value = "文件名字", paramType = "query", required = true, dataType = "string" ),

    })
    @ResponseBody
    @RequestMapping(path = "/querdImagePage", method = {RequestMethod.POST})
    public String querdImagePage(int connt,int pageNum,String fileName) {
        try {
            Page<AttachFile> page=new Page<>(connt,pageNum);
            Page page1=attachFileService.selectMapsPage(page,new EntityWrapper<AttachFile>().eq("type",fileName));
            return Result.Result(RC.SUCCESS, page1);
        } catch (Exception e) {
            return Result.Result("40001","上传文件出错", e);
        }
    }
    @ApiOperation(value = "删除指定的图片", notes = "删除指定的图片")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "token", value = "token", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "fileId", value = "每页显示的数量", paramType = "query", required = true, dataType = "string" ),
    })
    @ResponseBody
    @RequestMapping(path = "/DelectImagePage", method = {RequestMethod.POST})
    public String DelectImagePage(String fileId) {
        try {

                DataFileUtil.delectDBImage(fileId);

            return Result.Result(RC.SUCCESS);
        } catch (Exception e) {
            return Result.Result("40001","上传文件出错", e);
        }
    }
}

