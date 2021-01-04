package com.example.shop.api.constant;


/**
 * 凭证
 * @author zhoumin
 * @create 2018-07-11 15:22
 */

public class AccessToken {
	/**
	 *  获取到的凭证
	 */
	private String accessToken;
	
	/**
	 *  凭证有效时间，单位：秒
	 */
	private int expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
