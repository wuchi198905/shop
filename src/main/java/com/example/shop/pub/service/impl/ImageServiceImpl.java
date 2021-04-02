package com.example.shop.pub.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.shop.management.bean.Image;
import com.example.shop.pub.mapper.ImageMapper;
import com.example.shop.pub.service.ImageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.shop.management.bean.DTO.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员图片 服务实现类
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {
    @Autowired
    private ImageMapper  imageMapper;
    @Override
    public List<ImageDTO> imagePagination(ImageDTO images) {
        return imageMapper.imagePagination(images);
    }

}
