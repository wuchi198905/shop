package com.example.shop.pub.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-25
 */
@TableName("dynamic")
public class Dynamic extends Model<Dynamic> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dynamic_id", type = IdType.AUTO)
    private Integer dynamicId;
    /**
     * 发表id
     */
    @TableField("member_id")
    private Integer memberId;
    /**
     * 内容
     */
    private String content;
    /**
     * 被回复id
     */
    @TableField("reply_id")
    private Integer replyId;
    @TableField("creation_time")
    private Date creationTime;
    /**
     * 0  发帖  1回复
     */
    private String type;
    /**
     * 标题
     */
    private String title;


    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected Serializable pkVal() {
        return this.dynamicId;
    }

    @Override
    public String toString() {
        return "Dynamic{" +
        ", dynamicId=" + dynamicId +
        ", memberId=" + memberId +
        ", content=" + content +
        ", replyId=" + replyId +
        ", creationTime=" + creationTime +
        ", type=" + type +
        ", title=" + title +
        "}";
    }
}
