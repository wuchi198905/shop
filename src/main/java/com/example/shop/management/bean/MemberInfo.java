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

import java.io.Serializable;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-16
 */

@TableName("member_info")
public class MemberInfo extends Model<MemberInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员id
     */
    @TableId(value = "member_id", type = IdType.AUTO)
    private Integer memberId;
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
    private Double memberHeight;
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
    private Integer contactNumber;
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


    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public Integer getMemberAge() {
        return memberAge;
    }

    public void setMemberAge(Integer memberAge) {
        this.memberAge = memberAge;
    }

    public String getMemberEducation() {
        return memberEducation;
    }

    public void setMemberEducation(String memberEducation) {
        this.memberEducation = memberEducation;
    }

    public Double getMemberWeight() {
        return memberWeight;
    }

    public void setMemberWeight(Double memberWeight) {
        this.memberWeight = memberWeight;
    }

    public Double getMemberHeight() {
        return memberHeight;
    }

    public void setMemberHeight(Double memberHeight) {
        this.memberHeight = memberHeight;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getWechatAuthenticationStatus() {
        return wechatAuthenticationStatus;
    }

    public void setWechatAuthenticationStatus(String wechatAuthenticationStatus) {
        this.wechatAuthenticationStatus = wechatAuthenticationStatus;
    }

    public String getRealNameAuthenticationStatus() {
        return realNameAuthenticationStatus;
    }

    public void setRealNameAuthenticationStatus(String realNameAuthenticationStatus) {
        this.realNameAuthenticationStatus = realNameAuthenticationStatus;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getWeChatNumber() {
        return weChatNumber;
    }

    public void setWeChatNumber(String weChatNumber) {
        this.weChatNumber = weChatNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getCurrentEconomy() {
        return currentEconomy;
    }

    public void setCurrentEconomy(String currentEconomy) {
        this.currentEconomy = currentEconomy;
    }

    public String getCurrentDimension() {
        return currentDimension;
    }

    public void setCurrentDimension(String currentDimension) {
        this.currentDimension = currentDimension;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getWhetherUploadPictures() {
        return whetherUploadPictures;
    }

    public void setWhetherUploadPictures(String whetherUploadPictures) {
        this.whetherUploadPictures = whetherUploadPictures;
    }

    public String getMemberSource() {
        return memberSource;
    }

    public void setMemberSource(String memberSource) {
        this.memberSource = memberSource;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Integer getSts() {
        return sts;
    }

    public void setSts(Integer sts) {
        this.sts = sts;
    }

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
