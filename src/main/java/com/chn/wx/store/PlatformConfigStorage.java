package com.chn.wx.store;

/**
 * 平台信息持久化
 * @author wen
 *
 */
public interface PlatformConfigStorage {
	void updateVerifyTicket(String verify_ticketb);

	String getTicket();

	void updateAccessToken(String accessToken, int expiresIn);

	String getAccessToken();
	
	public boolean isAccessTokenExpired();
	   
}