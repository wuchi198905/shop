package com.example.shop.pub.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.shop.management.bean.DTO.MemberInfoDTO;
import com.example.shop.management.bean.MemberInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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



    List<MemberInfoDTO> HomepageDisplayPagination(MemberInfoDTO memberInfoDTO);

    List<MemberInfoDTO> Latestregisteredmembers();

    List<MemberInfo> getmemberInfopage(MemberInfo memberInfo);

    List<MemberInfoDTO> selectUserListPage(@Param("page") Page<MemberInfoDTO> page,@Param("memberInfoDTO")MemberInfoDTO memberInfoDTO);

    MemberInfoDTO selectUserInfo(MemberInfoDTO memberInfoDTO);
}
