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
 * 活动表
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@TableName("activity")
public class Activity extends Model<Activity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "activity_id", type = IdType.AUTO)
    private Integer activityId;
    /**
     * 开始时间
     */
    @TableField("starting_time")
    private Date startingTime;
    /**
     * 活动截止时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * h活动类型 0：免费活动  1 相亲
     */
    private String type;
    /**
     * 活动地区
     */
    @TableField("activity_area")
    private String activityArea;
    /**
     * 活动状态
     */
    @TableField("active_status")
    private String activeStatus;
    /**
     * 活动内容
     */
    private String activities;
    @TableField("creation_time")
    private Date creationTime;
    @TableField("update_time")
    private Date updateTime;
    /**
     * 0正常 1删除
     */
    private Integer sts;


    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(String activityArea) {
        this.activityArea = activityArea;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
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
        return this.activityId;
    }

    @Override
    public String toString() {
        return "Activity{" +
        ", activityId=" + activityId +
        ", startingTime=" + startingTime +
        ", endTime=" + endTime +
        ", type=" + type +
        ", activityArea=" + activityArea +
        ", activeStatus=" + activeStatus +
        ", activities=" + activities +
        ", creationTime=" + creationTime +
        ", updateTime=" + updateTime +
        ", sts=" + sts +
        "}";
    }
}
