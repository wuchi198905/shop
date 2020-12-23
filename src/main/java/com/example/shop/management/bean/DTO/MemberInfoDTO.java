package com.example.shop.management.bean.DTO;

import com.example.shop.management.bean.MemberInfo;

/**
 * 查询返回和查询会员类
 */
public class MemberInfoDTO extends MemberInfo {
    private String path;
    /**
     * 查询年龄的区间
     */
    private String maxAge;
    private String miniAge;




    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }

    public String getMiniAge() {
        return miniAge;
    }

    public void setMiniAge(String miniAge) {
        this.miniAge = miniAge;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
