package com.example.shop.management.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 征婚信息表
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@TableName("marriage")
public class Marriage extends Model<Marriage> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "marriage_id", type = IdType.AUTO)
    private Integer marriageId;
    @TableField("member_id")
    private Integer memberId;
    /**
     * 标题
     */
    private String title;
    /**
     * 年龄
     */
    private String ages;
    /**
     * 地区
     */
    private String area;
    /**
     * 身高
     */
    private String heights;
    /**
     * 体重
     */
    private String weight;
    private String status;
    /**
     * 描述
     */
    private String remarks;
    @TableField("creation_time")
    private Date creationTime;
    @TableField("update_time")
    private Date updateTime;


    public Integer getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(Integer marriageId) {
        this.marriageId = marriageId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHeights() {
        return heights;
    }

    public void setHeights(String heights) {
        this.heights = heights;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.marriageId;
    }

    @Override
    public String toString() {
        return "Marriage{" +
        ", marriageId=" + marriageId +
        ", memberId=" + memberId +
        ", title=" + title +
        ", ages=" + ages +
        ", area=" + area +
        ", heights=" + heights +
        ", weight=" + weight +
        ", status=" + status +
        ", remarks=" + remarks +
        ", creationTime=" + creationTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
