package com.example.shop.pub.bean;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-23
 */
@TableName("attach_file")
public class AttachFile extends Model<AttachFile> {

    private static final long serialVersionUID = 1L;

    @TableId("file_id")
    private String fileId;
    @TableField("file_type")
    private String fileType;
    @TableField("file_name")
    private String fileName;
    @TableField("save_name")
    private String saveName;
    private String state;
    private String sts;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.fileId;
    }

    @Override
    public String toString() {
        return "AttachFile{" +
        ", fileId=" + fileId +
        ", fileType=" + fileType +
        ", fileName=" + fileName +
        ", saveName=" + saveName +
        ", state=" + state +
        ", sts=" + sts +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
