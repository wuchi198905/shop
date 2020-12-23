package com.example.shop.system.bean;

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
 * 
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-22
 */
@TableName("role_func")
public class RoleFunc extends Model<RoleFunc> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_func_id", type = IdType.AUTO)
    private Integer roleFuncId;
    @TableField("role_id")
    private Integer roleId;
    @TableField("right_code")
    private String rightCode;
    private String sts;
    @TableField("sts_time")
    private Date stsTime;


    public Integer getRoleFuncId() {
        return roleFuncId;
    }

    public void setRoleFuncId(Integer roleFuncId) {
        this.roleFuncId = roleFuncId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRightCode() {
        return rightCode;
    }

    public void setRightCode(String rightCode) {
        this.rightCode = rightCode;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Date getStsTime() {
        return stsTime;
    }

    public void setStsTime(Date stsTime) {
        this.stsTime = stsTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.roleFuncId;
    }

    @Override
    public String toString() {
        return "RoleFunc{" +
        ", roleFuncId=" + roleFuncId +
        ", roleId=" + roleId +
        ", rightCode=" + rightCode +
        ", sts=" + sts +
        ", stsTime=" + stsTime +
        "}";
    }
}
