package com.chn.wx.api;

import org.apache.log4j.Logger;

import com.chn.common.Refresher;
import com.chn.wx.api.exception.WechatErrorException;

/**
 * 公众号第三方公众平台的 AccessToken 管理器
 * 
 * @class PlatFormTokenAccessor
 * @author lzxz1234
 * @description
 * @version v1.0
 */
public class PlatFormTokenAccessor {

	private static Logger log = Logger.getLogger(PlatFormTokenAccessor.class);
	private PlatFormTokenRefresher refresher;
	private Refresher<String> preAuthRefresher;

	public PlatFormTokenRefresher getRefresher() {
		if (refresher == null) {
			refresher = new PlatFormTokenRefresher();
		}
		return refresher;
	}

	public void setRefresher(PlatFormTokenRefresher refresher) {
		this.refresher = refresher;
	}

	public  Refresher<String> getPreAuthRefresher() {
		if (preAuthRefresher == null) {
			preAuthRefresher = new PreAuthRefresher();
		}
		return preAuthRefresher;
	}

	public void setPreAuthRefresher(Refresher<String> preAuthRefresher) {
		this.preAuthRefresher = preAuthRefresher;
	}

	public synchronized String getPreAuthCode() {
		return getPreAuthRefresher().get();
	}

	public synchronized String getAccessToken() throws WechatErrorException {
		return getRefresher().get();
	}

	public void updatePlatFormVerifyTicket(String componentVerifyTicket, String createTime) {
		getRefresher().setComponentVerifyTicket(componentVerifyTicket, createTime);
	}
}
