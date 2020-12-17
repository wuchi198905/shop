package com.example.shop.base;



import com.example.shop.management.bean.MemberInfo;

import javax.servlet.http.HttpSession;

public class SessionVehicle {
	
	private static final String SESSION_VEHICLE = "CHENGPEI_SESSION_VEHICLE";

	public static final String VHEICLE_ID = "SESSION_VEHICLE.VHEICLE_ID";
	public static final String USERNAME = "SESSION_VEHICLE.USERNAME";
	public static final String MOBILE = "SESSION_VEHICLE.MOBILE";
	public static final String MEMBER_ID = "SESSION_VEHICLE.MEMBER_ID";
	public static final String ACCOUNT_STATE = "SESSION_VEHICLE.ACCOUNT_STATE";
	public static final String SALT = "SESSION_VEHICLE.SALT";

	public static String get(String key) {
		String val = null;

		HttpSession session = HttpHelper.getHttpSession();
		MemberInfo vehicle = (MemberInfo) session.getAttribute(SESSION_VEHICLE);
		if (vehicle == null) {
			return "";
		}

		switch (key) {

		case MEMBER_ID:
			val = vehicle.getMemberId().toString();
			break;

		default:
			break;
		}
		return val;
	}

	/**
	 * 从session中读取会员对象
	 */
	public static MemberInfo getVehicleInfo() {
		HttpSession session = HttpHelper.getHttpSession();
		return (MemberInfo) session.getAttribute(SESSION_VEHICLE);
	}

	public static void reflesh(MemberInfo vehicleInfo) {
		HttpHelper.getHttpSession().setAttribute(SESSION_VEHICLE, vehicleInfo);
	}

	public static void destroy() {
		HttpHelper.getHttpSession().removeAttribute(SESSION_VEHICLE);
	}

	public static boolean isLogined() {
		MemberInfo vehicleInfo = (MemberInfo) HttpHelper.getHttpSession().getAttribute(SESSION_VEHICLE);
		return vehicleInfo != null;
	}
}
