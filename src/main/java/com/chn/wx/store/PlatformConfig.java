package com.chn.wx.store;

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
