package com.example.shop.base;


import com.example.shop.system.bean.UserInfo;

import javax.servlet.http.HttpSession;

public class SessionUSER {
    private static final String SESSION_USER = "CHENGPEI_SESSION_UserInfo";

    public static final String userId = "SESSION_VEHICLE.VHEICLE_ID";


    public static String get(String key) {
        String val = null;

        HttpSession session = HttpHelper.getHttpSession();
        UserInfo vehicle = (UserInfo) session.getAttribute(SESSION_USER);
        if (vehicle == null) {
            return "";
        }

        switch (key) {

            case userId:
                val = vehicle.getUserId().toString();
                break;

            default:
                break;
        }
        return val;
    }

    /**
     * 从session中读取会员对象
     */
    public static UserInfo getVehicleInfo() {
        HttpSession session = HttpHelper.getHttpSession();
        return (UserInfo) session.getAttribute(SESSION_USER);
    }

    public static void reflesh(UserInfo vehicleInfo) {
        HttpHelper.getHttpSession().setAttribute(SESSION_USER, vehicleInfo);
    }

    public static void destroy() {
        HttpHelper.getHttpSession().removeAttribute(SESSION_USER);
    }

    public static boolean isLogined() {
        UserInfo vehicleInfo = (UserInfo) HttpHelper.getHttpSession().getAttribute(SESSION_USER);
        return vehicleInfo != null;
    }
}
