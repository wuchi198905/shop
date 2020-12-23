package com.example.shop.pub.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.shop.base.connt.DataFileUtil;
import com.example.shop.base.json.RC;
import com.example.shop.base.json.Result;
import com.example.shop.pub.Utils.ImageType;
import com.example.shop.pub.bean.AttachFile;
import com.example.shop.pub.service.AttachFileService;
import io.swagger.annotations.Api;
import org.apache.http.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-23
 */
@Api(description = "统一上传图片的接口")
@RestController
@RequestMapping("/attachFile")
public class AttachFileController {
    @Autowired
    private AttachFileService attachFileService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadImage")
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
}

