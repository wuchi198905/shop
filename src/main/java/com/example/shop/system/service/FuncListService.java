package com.example.shop.system.service;

import com.example.shop.management.bean.DTO.FuncListDTO;
import com.example.shop.system.bean.FuncList;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-22
 */
public interface FuncListService extends IService<FuncList> {

    List<FuncListDTO> MenuPagination(FuncList funcList);
}
