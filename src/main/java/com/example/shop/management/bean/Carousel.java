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
 * 轮播图
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@TableName("carousel")
public class Carousel extends Model<Carousel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "carousel_id", type = IdType.AUTO)
    private Integer carouselId;
    /**
     * 图片链接
     */
    private String path;
    /**
     * 跳转类型
     */
    private String type;
    /**
     * 跳转的链接
     */
    @TableField("jump_link")
    private String jumpLink;
    /**
     * 轮播图文本内容
     */
    @TableField("jumped_text")
    private String jumpedText;
    /**
     * 顺序
     */
    private Integer order;
    /**
     * 是否显示
     */
    @TableField("whether_show")
    private String whetherShow;
    @TableField("creation_time")
    private Date creationTime;
    @TableField("update_time")
    private Date updateTime;
    /**
     * 0:正常  1已删除
     */
    private Integer sts;


    public Integer getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Integer carouselId) {
        this.carouselId = carouselId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJumpLink() {
        return jumpLink;
    }

    public void setJumpLink(String jumpLink) {
        this.jumpLink = jumpLink;
    }

    public String getJumpedText() {
        return jumpedText;
    }

    public void setJumpedText(String jumpedText) {
        this.jumpedText = jumpedText;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getWhetherShow() {
        return whetherShow;
    }

    public void setWhetherShow(String whetherShow) {
        this.whetherShow = whetherShow;
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

    public Integer getSts() {
        return sts;
    }

    public void setSts(Integer sts) {
        this.sts = sts;
    }

    @Override
    protected Serializable pkVal() {
        return this.carouselId;
    }

    @Override
    public String toString() {
        return "Carousel{" +
        ", carouselId=" + carouselId +
        ", path=" + path +
        ", type=" + type +
        ", jumpLink=" + jumpLink +
        ", jumpedText=" + jumpedText +
        ", order=" + order +
        ", whetherShow=" + whetherShow +
        ", creationTime=" + creationTime +
        ", updateTime=" + updateTime +
        ", sts=" + sts +
        "}";
    }
}
