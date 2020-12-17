package com.example.shop.management.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 咨询表
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@TableName("advisory")
public class Advisory extends Model<Advisory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "advisory_id", type = IdType.AUTO)
    private Integer advisoryId;
    /**
     * 标题
     */
    private String title;
    /**
     * 咨询类别
     */
    private String type;
    /**
     * 咨询内容
     */
    private String content;
    /**
     * 会员id
     */
    @TableField("member_id")
    private String memberId;


    public Integer getAdvisoryId() {
        return advisoryId;
    }

    public void setAdvisoryId(Integer advisoryId) {
        this.advisoryId = advisoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    protected Serializable pkVal() {
        return this.advisoryId;
    }

    @Override
    public String toString() {
        return "Advisory{" +
        ", advisoryId=" + advisoryId +
        ", title=" + title +
        ", type=" + type +
        ", content=" + content +
        ", memberId=" + memberId +
        "}";
    }
}
