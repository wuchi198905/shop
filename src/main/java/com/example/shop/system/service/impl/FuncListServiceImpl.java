package com.example.shop.system.service.impl;

import com.example.shop.management.bean.DTO.FuncListDTO;
import com.example.shop.system.bean.FuncList;
import com.example.shop.system.mapper.FuncListMapper;
import com.example.shop.system.service.FuncListService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-22
 */
@Service
public class FuncListServiceImpl extends ServiceImpl<FuncListMapper, FuncList> implements FuncListService {
    @Autowired
    private FuncListMapper funcListMDAO;

    @Override
    public List<FuncListDTO> MenuPagination(FuncList funcList) {
        List<FuncListDTO> list=new ArrayList<>();
        FuncListDTO mvo1 = new FuncListDTO();
        mvo1.setSts("A");
        mvo1.setMenuLevel(1);
        List<FuncListDTO> list1=funcListMDAO.queryList(mvo1);
        for (int i=0;i<list1.size();i++){
            FuncListDTO mvo2 = new FuncListDTO();
            mvo2.setSts("A");
            mvo2.setMenuLevel(2);
            mvo2.setParentId(list1.get(i).getFuncId());
            List<FuncListDTO> list2=funcListMDAO.queryList(mvo2);
            list.get(i).setSubFuncList(list2);
            for(int j=0;j<list2.size();i++){
                FuncListDTO mvo3 = new FuncListDTO();
                mvo3.setSts("A");
                mvo3.setMenuLevel(2);
                mvo3.setParentId(list1.get(i).getFuncId());
                List<FuncListDTO> list3=funcListMDAO.queryList(mvo1);
                list2.get(j).setSubFuncList(list3);
            }

        }
        return list1;
    }
}
