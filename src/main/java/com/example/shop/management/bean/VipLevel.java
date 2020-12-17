package com.example.shop.management.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员级别表
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@TableName("vip_level")
public class VipLevel extends Model<VipLevel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "level_id", type = IdType.AUTO)
    private Integer levelId;
    /**
     * 会员名称
     */
    @TableField("level_name")
    private String levelName;
    /**
     * 标识
     */
    private String logo;
    /**
     * 会员价格
     */
    private BigDecimal price;
    /**
     * 创建时间
     */
    @TableField("creation_time")
    private Date creationTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        return this.levelId;
    }

    @Override
    public String toString() {
        return "VipLevel{" +
        ", levelId=" + levelId +
        ", levelName=" + levelName +
        ", logo=" + logo +
        ", price=" + price +
        ", creationTime=" + creationTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
