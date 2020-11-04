package com.example.shop.management.mapper;

import com.example.shop.management.bean.LeUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-11-03
 */
@Mapper
@Repository
public interface LeUserMapper extends BaseMapper<LeUser> {

}
