package com.example.shop.management.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.shop.base.SessionVehicle;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Image;
import com.example.shop.pub.bean.AttachFile;
import com.example.shop.pub.service.AttachFileService;
import com.example.shop.pub.service.ImageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会员图片 前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@RestController
@RequestMapping("/memberInfo/image")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private AttachFileService attachFileService;

    private String sava_path = "http://192.168.124.9:88";

    @ApiOperation(value = "上传图片", notes = "上传图片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Token", value = "token标记", required = true),
            @ApiImplicitParam(name = "fileId", value = "上传图片后获取的fileId", paramType = "query", required = true, dataType = "String" )
    })
    @RequestMapping(path = "/save_photo", method = {RequestMethod.POST})
    public String addDish(String fileId) throws Exception {
        String VHEICLEiD = SessionVehicle.get(SessionVehicle.MEMBER_ID);
        Image image = new Image();
        image.setMemberId(VHEICLEiD);
        int mun = imageService.selectCount(new EntityWrapper<Image>().eq("member_id", VHEICLEiD));
        if (mun > 0) {
            image.setType("B");
        } else {
            image.setType("A");
        }
        AttachFile file1=attachFileService.selectOne(new EntityWrapper<AttachFile>().eq("file_id",fileId));
        image.setPath(file1.getSaveName());
        image.setStatus("0");
        image.setCreationTime(new Date());
        imageService.insert(image);
        // 转存文件到指定的路径
        return Result.Result(RC.SUCCESS);


    }



/**
 * 设置头衔接口
 */

@ApiOperation(value = "设置头衔接口", notes = "设置头衔接口")
@ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", dataType = "String", name = "Token", value = "token标记", required = true),
        @ApiImplicitParam(name = "imageId", value = "图片id", paramType = "query", required = true, dataType = "String"),

})
@RequestMapping(value = "Latestregisteredmembers", method = RequestMethod.POST)
@ResponseBody
public String Latestregisteredmembers(Integer imageId){
        String memberId=SessionVehicle.get(SessionVehicle.MEMBER_ID);
        Image image=imageService.selectOne(new EntityWrapper<Image>().eq("member_id",memberId).eq("type","A"));
        image.setType("B");
        imageService.updateById(image);
        Image image2=new Image();
        image2.setType("A");
        image2.setImageId(imageId);
        imageService.updateById(image2);
        return Result.Result(RC.SUCCESS);
        }
/**
 * 查询我的图片
 */

@ApiOperation(value = "查询我的照片", notes = "查询我的照片")
@ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", dataType = "String", name = "Token", value = "token标记", required = true),

})
@RequestMapping(value = "QueryMyphotos", method = RequestMethod.POST)
@ResponseBody
public String Queryphotos(){
        String memberId=SessionVehicle.get(SessionVehicle.MEMBER_ID);
        List<Image>  image=imageService.selectList(new EntityWrapper<Image>().eq("member_id",memberId));

        return Result.Result(RC.SUCCESS,image);
        }
        }

