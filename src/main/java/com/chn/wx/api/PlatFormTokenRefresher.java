package com.chn.wx.api;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.chn.common.HttpUtils;
import com.chn.common.Refresher;
import com.chn.wx.api.exception.ErrorUtils;
import com.chn.wx.dto.App;
import com.chn.wx.store.PlatformConfigStorage;
import com.chn.wx.store.PlatformConfigYamlStorage;
import com.chn.wx.template.PlatFormMessage;
import com.chn.wx.vo.result.PlatFormAccessTokenResult;

public class PlatFormTokenRefresher extends Refresher<String> {
	private static Logger log = Logger.getLogger(PlatFormTokenRefresher.class);
	private String platformTokenUrl = WeiXinURL.PLATFORM_GET_ACCESSTOKEN;
	private PlatformConfigStorage configStorage;
	
	
	public PlatformConfigStorage getConfigStorage() {
		if(configStorage == null)
			configStorage = new PlatformConfigYamlStorage();
		return configStorage;
	}

	public void setConfigStorage(PlatformConfigStorage configStorage) {
		this.configStorage = configStorage;
	}

	public void setComponentVerifyTicket(String componentVerifyTicket, String createTime) {
		getConfigStorage().updateVerifyTicket(componentVerifyTicket,createTime);
	}

	@Override
	public String refresh() {
		log.info("更新平台component_access_token");
		PlatFormAccessTokenResult result = null;
		if (!getConfigStorage().isAccessTokenExpired())
			return getConfigStorage().getAccessToken();
		String postJson = PlatFormMessage.wrapGetAccessToken(App.Info.id, App.Info.secret, getConfigStorage().getTicket());
		String respJson = HttpUtils.post(platformTokenUrl, postJson);
		result = JSON.parseObject(respJson, PlatFormAccessTokenResult.class);

		ErrorUtils.checkWXError(result);

		log.debug("获取的component_access_token:" + result);
		getConfigStorage().updateAccessToken(result.getComponentAccessToken(), result.getExpiresIn());
		return result.getComponentAccessToken();
	}

	@Override
	public boolean isExpired() {
		return getConfigStorage().isAccessTokenExpired();
	}

	@Override
	public String get() {
		current = getConfigStorage().getAccessToken();
		return super.get();
	}
}
