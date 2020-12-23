package com.example.shop.pub.mapper;

import com.example.shop.management.bean.Image;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.shop.management.bean.DTO.ImageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 会员图片 Mapper 接口
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Mapper

public interface ImageMapper extends BaseMapper<Image> {

    List<ImageDTO> imagePagination(ImageDTO images);
}
