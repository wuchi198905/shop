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
import org.springframework.web.bind.annotation.*;

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


}

