package com.example.shop.management.mapper;

import com.example.shop.management.bean.MemberInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Mapper
@Repository
public interface MemberInfoMapper extends BaseMapper<MemberInfo> {

}
