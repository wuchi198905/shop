package com.example.shop.system.mapper;

import com.example.shop.management.bean.DTO.FuncListDTO;
import com.example.shop.system.bean.FuncList;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-22
 */
@Mapper
public interface FuncListMapper extends BaseMapper<FuncList> {

    List<FuncListDTO> queryList(FuncList mvo);

    List<FuncListDTO> queryLevel2FuncByUserId(String userId);

    List<FuncListDTO> queryLevel3FuncByUserId(String userId);
}
