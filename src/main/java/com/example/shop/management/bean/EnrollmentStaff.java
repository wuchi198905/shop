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
 * 活动报名人员
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@TableName("enrollment_staff")
public class EnrollmentStaff extends Model<EnrollmentStaff> {

    private static final long serialVersionUID = 1L;

    /**
     * 人员id
     */
    @TableId(value = "personnel_id", type = IdType.AUTO)
    private Integer personnelId;
    /**
     * 0：预约中， 1 待审核  2 报名成功 
     */
    @TableField("activity_id")
    private String activityId;
    private Integer status;
    /**
     * 参加会员id
     */
    @TableField("member_id")
    private String memberId;
    @TableField("creation_time")
    private Date creationTime;
    @TableField("update_time")
    private Date updateTime;
    /**
     * 0正常  1删除
     */
    private Integer sts;


    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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
        return this.personnelId;
    }

    @Override
    public String toString() {
        return "EnrollmentStaff{" +
        ", personnelId=" + personnelId +
        ", activityId=" + activityId +
        ", status=" + status +
        ", memberId=" + memberId +
        ", creationTime=" + creationTime +
        ", updateTime=" + updateTime +
        ", sts=" + sts +
        "}";
    }
}
