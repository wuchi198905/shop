package com.example.shop.management.bean.DTO;

import com.example.shop.system.bean.FuncList;

import java.util.ArrayList;
import java.util.List;

public class FuncListDTO extends FuncList {
    private List<FuncListDTO> subFuncList = new ArrayList<FuncListDTO>();

    public List<FuncListDTO> getSubFuncList() {
        return subFuncList;
    }

    public void setSubFuncList(List<FuncListDTO> subFuncList) {
        this.subFuncList = subFuncList;
    }
}
