package com.example.shop.management.bean;

import java.io.Serializable;

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
 * @since 2020-11-03
 */
@TableName("le_user")
public class LeUser extends Model<LeUser> {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private Integer userId;
    /**
     * 登录帐号
     */
    @TableField("user_account")
    private String userAccount;
    /**
     * 登录密码
     */
    @TableField("user_password")
    private String userPassword;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 用户状态 A:正常  B 禁用
     */
    @TableField("user_state")
    private String userState;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "LeUser{" +
        ", userId=" + userId +
        ", userAccount=" + userAccount +
        ", userPassword=" + userPassword +
        ", userName=" + userName +
        ", userState=" + userState +
        "}";
    }
}
