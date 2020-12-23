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
 * 会员图片
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@TableName("image")
public class Image extends Model<Image> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "image_id", type = IdType.AUTO)
    private Integer imageId;
    /**
     * 路径
     */
    private String path;
    /**
     * 类型  A  首页展示   B其他
     */
    private String type;
    /**
     * 状态  1  审核通过  0未审核  2  审核未通过
     */
    private String status;
    /**
     * 会员id
     */
    @TableField("member_id")
    private String memberId;
    /**
     * 审核人id
     */
    @TableField("user_id")
    private String userId;
    @TableField("creation_time")
    private Date creationTime;
    @TableField("update_time")
    private Date updateTime;
    /**
     * 0 正常  1 删除
     */
    private Integer sts;


    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return this.imageId;
    }

    @Override
    public String toString() {
        return "Image{" +
        ", imageId=" + imageId +
        ", path=" + path +
        ", type=" + type +
        ", status=" + status +
        ", memberId=" + memberId +
        ", userId=" + userId +
        ", creationTime=" + creationTime +
        ", updateTime=" + updateTime +
        ", sts=" + sts +
        "}";
    }
}
