package com.chn.wx.store;
/**
 * 微信开发平台验证参数
 * @author wuyongwen
 * @Date 2016年3月22日下午3:10:42
 */
public class PlatformConfig {
	private String verifyTicket;
	private String accessToken;
	private long expiresIn;
	public String getVerifyTicket() {
		return verifyTicket;
	}
	public void setVerifyTicket(String verifyTicket) {
		this.verifyTicket = verifyTicket;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
}
