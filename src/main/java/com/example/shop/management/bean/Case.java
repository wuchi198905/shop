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
 * 幸福案例
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@TableName("caseinfo")
public class Case extends Model<Case> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "case_id", type = IdType.AUTO)
    private Integer caseId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    @TableField("creation_time")
    private Date creationTime;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.caseId;
    }

    @Override
    public String toString() {
        return "Case{" +
        ", caseId=" + caseId +
        ", title=" + title +
        ", content=" + content +
        ", creationTime=" + creationTime +
        "}";
    }
}
