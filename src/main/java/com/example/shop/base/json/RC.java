package com.example.shop.base.json;

public enum RC {
    SUCCESS("00000", "成功"),


    // 注册 12
    REGIST_PARAM_TYPE_INVALID("10001", "获取验证码失败"),
    REGIST_PARAM_SMSCODE_INVALID("10002", "请重新获取验证码"),
    REGIST_PARAM_MOBILE_OCCUPIED("10003", "该手机号码已注册，请直接登录"),
    REGIST_PARAM_MOBILE_INVALID("1202", "手机号码无效"),
    // 完善信息 13
    PERFCT_ADDRESS_ISNOTNULL("110001", "详细地址不能为空"),
    PERFCT_MEMBERNAME_ISNOTNULL("110002", "姓名不能为空"),
    PERFCT_MEMBERGRNDER_ISNOTNULL("110003", "请填写性别"),
    PERFCT_MEBERAGE_ISNOTNULL("110004", "年龄不能为空"),
    PERFCT_MEMBEREUCATTION_ISNOTNULL("110005", "学历不能为空"),
    PERFCT_MARITALsTATUS_ISNOTNULL("110006", "婚姻状态不能为空"),
    PERFCT_PROVINCE_ISNOTNULL("110007", "所在省不能为空"),
    PERFCT_CITY_ISNOTNULL("110008", "所在市不能为空"),
    PERFCT_IDNUMBER_ISNOTNULL("110009", "身份证格式不正确"),
    FILE_TYPE_Ereey("200001", "图片格式不正确"),
    /**
     * 登录
     */
    LOGIN_USER_ISNOT("30001", "用户不存在"),
    /**
     * 活动报名
     */
    Enrollment_activityId_ISNOT("30001", "用户不存在"),
    /**
     * 登录
     */
    system_erry("99999", "系统错误，请联系客服");

  
    public String code;
    public String msg;
    private RC(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
