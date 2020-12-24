package com.example.shop.management.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.shop.base.SessionVehicle;
import com.example.shop.base.connt.DataFileUtil;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.management.bean.Image;
import com.example.shop.pub.Utils.ImageType;
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
@Api(description = "个人照片的管理接口")
@RestController
@RequestMapping("/memberInfo/image")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private AttachFileService attachFileService;

    @ApiOperation(value = "添加个人图片", notes = "添加个人图片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),
            @ApiImplicitParam(name = "fileId", value = "上传图片后获取的fileId", paramType = "query", required = true, dataType = "string")
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
        AttachFile file1 = attachFileService.selectOne(new EntityWrapper<AttachFile>().eq("file_id", fileId));
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
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),
            @ApiImplicitParam(name = "imageId", value = "图片id", paramType = "query", required = true, dataType = "string"),

    })
    @RequestMapping(value = "Latestregisteredmembers", method = RequestMethod.POST)
    @ResponseBody
    public String Latestregisteredmembers(Integer imageId) {
        String memberId = SessionVehicle.get(SessionVehicle.MEMBER_ID);
        Image image = imageService.selectOne(new EntityWrapper<Image>().eq("member_id", memberId).eq("type", "A"));
        image.setType("B");
        imageService.updateById(image);
        Image image2 = new Image();
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
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "Token", value = "token标记", required = true),

    })
    @RequestMapping(value = "QueryMyphotos", method = RequestMethod.POST)
    @ResponseBody
    public String Queryphotos() {
        String memberId = SessionVehicle.get(SessionVehicle.MEMBER_ID);
        List<Image> image = imageService.selectList(new EntityWrapper<Image>().eq("member_id", memberId));

        return Result.Result(RC.SUCCESS, image);
    }


    /**
     * 上传文件
     *
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
            if (!(ImageType.IMG_TYPE_DMG.equals(suffix.toUpperCase()) ||
                    ImageType.IMG_TYPE_GIF.equals(suffix.toUpperCase()) ||
                    ImageType.IMG_TYPE_JPEG.equals(suffix.toUpperCase()) ||
                    ImageType.IMG_TYPE_JPG.equals(suffix.toUpperCase()) ||
                    ImageType.IMG_TYPE_PNG.equals(suffix.toUpperCase()) ||
                    ImageType.IMG_TYPE_SVG.equals(suffix.toUpperCase()))) {
                return Result.Result(RC.FILE_TYPE_Ereey);
            }
            String fileId = DataFileUtil.saveDBImage(file);

            AttachFile file1 = attachFileService.selectOne(new EntityWrapper<AttachFile>().eq("file_id", fileId));
            return Result.Result(RC.SUCCESS, file1);
        } catch (Exception e) {
            return Result.Result("40001", "上传文件出错", e);
        }
    }
}