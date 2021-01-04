package com.example.shop.api.bean;

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
 * @since 2021-01-04
 */
@TableName("wx_config")
public class WxConfig extends Model<WxConfig> {

    private static final long serialVersionUID = 1L;
    public final static String ACCESS_TOKEN_TIME = "ACCESS_TOKEN_TIME";
    @TableId("config_id")
    private String configId;
    @TableField("config_name")
    private String configName;
    @TableField("config_key")
    private String configKey;
    @TableField("config_value")
    private String configValue;
    private Integer type;
    private String remark;
    @TableField("update_time")
    private Date updateTime;


    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.configId;
    }

    @Override
    public String toString() {
        return "WxConfig{" +
        ", configId=" + configId +
        ", configName=" + configName +
        ", configKey=" + configKey +
        ", configValue=" + configValue +
        ", type=" + type +
        ", remark=" + remark +
        ", updateTime=" + updateTime +
        "}";
    }
}
