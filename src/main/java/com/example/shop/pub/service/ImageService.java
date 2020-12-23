package com.example.shop.pub.service;

import com.example.shop.management.bean.Image;
import com.baomidou.mybatisplus.service.IService;
import com.example.shop.management.bean.DTO.ImageDTO;

import java.util.List;

/**
 * <p>
 * 会员图片 服务类
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
public interface ImageService extends IService<Image> {

    List<ImageDTO> imagePagination(ImageDTO images);
}
