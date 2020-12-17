package com.example.shop.base.json;

public enum RC {
    SUCCESS("00000", "成功"),

    // 账号操作类
    // 登录 11
    LOGIN_USERNAME_PASSWORD_CANNOT_NULL("1101", "用户名或密码不能为空"),
    LOGIN_USERNAME_PASSWORD_ERROR("1102", "用户名或密码错误"),
    LOGIN_USERNAME_ACCOUNT_TYPE("1103", "用户没有权限登录该APP"),
    LOGIN_USERNAME_LOCKED("1104", "您的账号已被锁定，请联系客服"),
    LOGIN_USERNAME_INVALID("1105", "您的账号已失效，请联系客服"),
    LOGIN_MANAGER_USERNAME_ACCOUNT_TYPE("1106", "用户没有权限登录该平台"),
    // 注册 12
    REGIST_PARAM_TYPE_INVALID("10001", "获取验证码失败"),
    REGIST_PARAM_SMSCODE_INVALID("10002", "请重新获取验证码"),
    REGIST_PARAM_MOBILE_OCCUPIED("1205", "该手机号码已注册，请直接登录"),
    REGIST_PARAM_MOBILE_INVALID("1202", "手机号码无效"),

    REGIST_PARAM_PASSWORD_INVALID("1204", "密码格式无效（必须为6-16位字母+数字）"),

    REGIST_PARAM_SMSCODE_TYPE_INVALID("1206", "短信验证码类型无效"),
    REGIST_PARAM_USERNAME_INVALID("1207", "用户名无效（必须为6-16位字母或数字）"),
    REGIST_PARAM_USERNAME_OCCUPIED("1208", "该用户名已注册，请使用其他用户名"),
    REGIST_PARAM_STAFF_NAME_INVALID("1209", "员工姓名无效"),
    REGIST_PARAM_ROLE_ID_INVALID("1210", "请选择角色权限"),
    REGIST_PARAM_MEMBER_SUB_INVALID("1211", "员工编码无效");
    public String code;
    public String msg;
    private RC(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
