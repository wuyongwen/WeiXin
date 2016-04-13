package com.chn.wx.api;

import static com.chn.common.StringTemplate.compile;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.chn.common.HttpUtils;
import com.chn.common.Refresher;
import com.chn.common.StringTemplate;
import com.chn.wx.dto.App;
import com.chn.wx.store.PlatformConfigStorage;
import com.chn.wx.store.PlatformConfigYamlStorage;
import com.chn.wx.template.PlatFormMessage;
import com.chn.wx.vo.result.PlatFormGetPreAuthCodeResult;

public class PreAuthRefresher extends Refresher<String> {
	private static Logger log = Logger.getLogger(PreAuthRefresher.class);
	private StringTemplate getPreAuthCodeUrl = compile(WeiXinURL.PLATFORM_GET_PRE_AUTHCODE);

	protected PlatformConfigStorage configStorage;
	protected PlatFormTokenAccessor platFormTokenAccessor;
	public  PlatformConfigStorage getConfigStorage() {
		if (configStorage == null)
			configStorage = new PlatformConfigYamlStorage();
		return configStorage;
	}

	public void setConfigStorage(PlatformConfigStorage configStorage) {
		this.configStorage = configStorage;
	}

	public PlatFormTokenAccessor getPlatFormTokenAccessor() {
		if(platFormTokenAccessor == null)
			platFormTokenAccessor = new PlatFormTokenAccessor();
		return platFormTokenAccessor;
	}

	public void setPlatFormTokenAccessor(PlatFormTokenAccessor platFormTokenAccessor) {
		this.platFormTokenAccessor = platFormTokenAccessor;
	}

	@Override
	public String refresh() {

		Map<String, Object> params = new HashMap<>();
		params.put("component_access_token", getPlatFormTokenAccessor().getAccessToken());
		String urlLocation = getPreAuthCodeUrl.replace(params);

		PlatFormGetPreAuthCodeResult result = null;
		try {
			String respJson = HttpUtils.post(urlLocation, PlatFormMessage.wrapGetPreAuthCode(App.Info.id));
			result = JSON.parseObject(respJson, PlatFormGetPreAuthCodeResult.class);
		} catch (Exception e) {
			log.error("请求 PreAuthCode 失败，继续采用之前 AuthCode！", e);
			return current;
		}
		log.info("更新 PreAuthCode：" + result.getPreAuthCode());
		getConfigStorage().updatePreAuthCode(result.getPreAuthCode(), result.getExpiresIn());
		return result.getPreAuthCode();
	}

	@Override
	public boolean isExpired() {
		return getConfigStorage().isPreAuthCodeExpired();
	}

}