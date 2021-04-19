package com.example.shop.management.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */
@Data
@TableName("member_info")
public class MemberInfo extends Model<MemberInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员id
     */
    @TableId(value = "member_id", type = IdType.AUTO)
    private Integer memberId;
    /**
     * 用户名  显示用并非登录
     */
    public   String userName;
    /**
     * 姓名
     */

    @TableField("member_name")
    private String memberName;
    /**
     * 性别  ：男：A  女：B
     */

    @TableField("member_gender")
    private String memberGender;
    /**
     * 年龄
     */

    @TableField("member_age")
    private Integer memberAge;
    /**
     * 学历
     */
    @TableField("member_education")
    private String memberEducation;
    /**
     * 体重
     */
    @TableField("member_weight")
    private Double memberWeight;
    /**
     * 身高
     */
    @TableField("member_height")
    private String memberHeight;
    /**
     * 0  未婚 1已婚 2离异 3丧偶 
     */
    @TableField("marital_status")
    private String maritalStatus;
    /**
     * 0 未认证  1 已认证
     */
    @TableField("WeChat_authentication_status")
    private String wechatAuthenticationStatus;
    /**
     * 实名认证状态  0未认证  1已认证
     */
    @TableField("real_name_authentication_status")
    private String realNameAuthenticationStatus;
    /**
     * 会员级别  1 普通会员
     */
    @TableField("membership_level")
    private String membershipLevel;
    private String openid;
    /**
     * 联系电话
     */
    @TableField("contact_number")
    private String contactNumber;
    /**
     * 邮箱
     */
    private String mailbox;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String area;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 密码
     */
    private String password;
    /**
     * 身份证编号
     */
    @TableField("id_number")
    private String idNumber;
    /**
     * 帐号
     */
    private String account;
    /**
     * 微信号
     */
    @TableField("we_chat_number")
    private String weChatNumber;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 当前所在城市
     */
    @TableField("current_city")
    private String currentCity;
    /**
     * 当前所在的经
     */
    @TableField("current_economy")
    private String currentEconomy;
    /**
     * 当前所在的维度
     */
    @TableField("current_dimension")
    private String currentDimension;
    /**
     * 在线状态
     */
    private String online;
    /**
     * 是否上传图片
     */
    @TableField("whether_upload_pictures")
    private String whetherUploadPictures;
    /**
     * 会员来源
     */
    @TableField("member_source")
    private String memberSource;
    /**
     * 星座
     */
    private String constellation;
    /**
     * 0正常  1异常
     */
    @TableField("account_status")
    private String accountStatus;
    /**
     * 上次登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;
    /**
     * 注册时间
     */
    @TableField("registration_time")
    private Date registrationTime;
    /**
     * 0正常1删除
     */
    private Integer sts;
    /**
     * 宗教信仰
     */
    @TableField("religious_beliefs")
    private String religiousBeliefs;
    /**
     * 是否吸烟
     */
    private String smoking;
    /**
     * 公司性质
     */
    @TableField("nature_companature")
    private String natureCompanature;
    /**
     * 是否饮酒
     */
    private String drinking;
    /**
     * 排行
     */
    private String ranking;
    /**
     * 异地恋
     */
    @TableField("long_distance_relationships")
    private String longDistanceRelationships;
    /**
     * 是否要小孩
     */
    private String child;
    /**
     * 是否愿意和父母同住
     */
    @TableField("live_with")
    private String liveWith;
    /**
     * 个人介绍
     */
    @TableField("personal_introduction")
    private String personalIntroduction;



    @Override
    protected Serializable pkVal() {
        return this.memberId;
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
        ", memberId=" + memberId +
        ", memberName=" + memberName +
        ", memberGender=" + memberGender +
        ", memberAge=" + memberAge +
        ", memberEducation=" + memberEducation +
        ", memberWeight=" + memberWeight +
        ", memberHeight=" + memberHeight +
        ", maritalStatus=" + maritalStatus +
        ", wechatAuthenticationStatus=" + wechatAuthenticationStatus +
        ", realNameAuthenticationStatus=" + realNameAuthenticationStatus +
        ", membershipLevel=" + membershipLevel +
        ", openid=" + openid +
        ", contactNumber=" + contactNumber +
        ", mailbox=" + mailbox +
        ", province=" + province +
        ", city=" + city +
        ", area=" + area +
        ", address=" + address +
        ", password=" + password +
        ", idNumber=" + idNumber +
        ", account=" + account +
        ", weChatNumber=" + weChatNumber +
        ", birthday=" + birthday +
        ", currentCity=" + currentCity +
        ", currentEconomy=" + currentEconomy +
        ", currentDimension=" + currentDimension +
        ", online=" + online +
        ", whetherUploadPictures=" + whetherUploadPictures +
        ", memberSource=" + memberSource +
        ", constellation=" + constellation +
        ", accountStatus=" + accountStatus +
        ", lastLoginTime=" + lastLoginTime +
        ", registrationTime=" + registrationTime +
        ", sts=" + sts +
        "}";
    }
}
