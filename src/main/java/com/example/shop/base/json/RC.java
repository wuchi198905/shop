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
    PERFCT_PARAM_SMSCODE_INVALID("10002", "请重新获取验证码"),
    PERFCT_PARAM_MOBILE_OCCUPIED("1205", "该手机号码已注册，请直接登录"),
    PERFCT_PARAM_MOBILE_INVALID("1202", "手机号码无效");
    public String code;
    public String msg;
    private RC(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
