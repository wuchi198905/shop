package com.example.shop.pub.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-25
 */
@TableName("area_info")
public class AreaInfo extends Model<AreaInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @TableId("area_code")
    private String areaCode;
    /**
     * 上一级编码
     */
    @TableField("parent_code")
    private String parentCode;
    /**
     * 名称
     */
    @TableField("area_name")
    private String areaName;
    @TableField("area_level")
    private Integer areaLevel;
    @TableField("amap_code")
    private String amapCode;
    private String longitude;
    private String latitude;
    private String radii;


    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getAmapCode() {
        return amapCode;
    }

    public void setAmapCode(String amapCode) {
        this.amapCode = amapCode;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getRadii() {
        return radii;
    }

    public void setRadii(String radii) {
        this.radii = radii;
    }

    @Override
    protected Serializable pkVal() {
        return this.areaCode;
    }

    @Override
    public String toString() {
        return "AreaInfo{" +
        ", areaCode=" + areaCode +
        ", parentCode=" + parentCode +
        ", areaName=" + areaName +
        ", areaLevel=" + areaLevel +
        ", amapCode=" + amapCode +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", radii=" + radii +
        "}";
    }
}
