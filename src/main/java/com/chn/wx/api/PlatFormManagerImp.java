package com.chn.wx.api;

import static com.chn.common.StringTemplate.compile;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.chn.common.HttpUtils;
import com.chn.common.StringTemplate;
import com.chn.wx.dto.App;
import com.chn.wx.template.PlatFormMessage;
import com.chn.wx.vo.result.PlatFormGetAuthAccessResult;
import com.chn.wx.vo.result.PlatFormGetAuthInfoResult;
import com.chn.wx.vo.result.PlatFormGetAuthorizerInfoResult;
import com.chn.wx.vo.result.PlatFormGetAuthorizerOptionResult;
import com.chn.wx.vo.result.PlatFormSetAuthorizerOptionResult;

/**
 * 公众号第三方平台可用主动调用
 * 
 * @class PlatFormManager
 * @author lzxz1234
 * @description
 * @version v1.0
 */
public class PlatFormManagerImp implements PlatFormManager {

	private static Logger log = Logger.getLogger(PlatFormManager.class);

	private static StringTemplate getLoginUrl = compile(WeiXinURL.PLATFORM_LOGINURL);
	private static StringTemplate getAuthInfoUrl = compile(WeiXinURL.PLATFORM_GET_AUTHINFO);
	private static StringTemplate getAuthAccessTokenUrl = compile(WeiXinURL.PLATFORM_GET_AUTHACCESSTOKEN);
	private static StringTemplate getAuthorizerInfoUrl = compile(WeiXinURL.PLATFORM_GET_AUTHORIZER_INFO);
	private static StringTemplate getAuthorizerOptionUrl = compile(WeiXinURL.PLATFORM_GET_AUTHORIZER_OPTION);
	private static StringTemplate setAuthorizerOptionUrl = compile(WeiXinURL.PLATFORM_SET_AUTHORIZER_OPTION);

	private PlatFormTokenAccessor platFormTokenAccessor;
	
	public PlatFormTokenAccessor getPlatFormTokenAccessor() {
		if(platFormTokenAccessor==null)
			platFormTokenAccessor = new PlatFormTokenAccessor();
		return platFormTokenAccessor;
	}

	public void setPlatFormTokenAccessor(PlatFormTokenAccessor platFormTokenAccessor) {
		this.platFormTokenAccessor = platFormTokenAccessor;
	}
	@Override
	public String getLoginUrl(String url) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("component_appid", App.Info.id);
		params.put("pre_auth_code", this.getPreAuthCode());
		params.put("loogined_redirect_url", url);
		return getLoginUrl.replace(params);
	}
	@Override
	public String getLoginUrl() {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("component_appid", App.Info.id);
		params.put("pre_auth_code", this.getPreAuthCode());
		params.put("loogined_redirect_url", App.Info.loginedUrl);
		return getLoginUrl.replace(params);
	}
	
	@Override
	public String getPreAuthCode() {
		return getPlatFormTokenAccessor().getPreAuthCode();
	}

	@Override
	public PlatFormGetAuthInfoResult getAuthInfo(String authCode) {

		Map<String, Object> params = new HashMap<>();
		params.put("component_access_token", getPlatFormTokenAccessor().getAccessToken());
		String urlLocation = getAuthInfoUrl.replace(params);
		String postContent = PlatFormMessage.wrapGetAuthInfo(App.Info.id, authCode);
		String respJson = HttpUtils.post(urlLocation, postContent);
		return JSON.parseObject(respJson, PlatFormGetAuthInfoResult.class);
	}

	@Override
	public PlatFormGetAuthAccessResult getAuthAccessToken(String authAppId, String refreshToken) {
		Map<String, Object> params = new HashMap<>();
		params.put("component_access_token", getPlatFormTokenAccessor().getAccessToken());
		String urlLocation = getAuthAccessTokenUrl.replace(params);
		String postContent = PlatFormMessage.wrapGetAuthAccessToken(App.Info.id, authAppId, refreshToken);
		String respJson = HttpUtils.post(urlLocation, postContent);
		return JSON.parseObject(respJson, PlatFormGetAuthAccessResult.class);
	}

	@Override
	public PlatFormGetAuthorizerInfoResult getAuthorizerInfo(String authAppId) {

		Map<String, Object> params = new HashMap<>();
		params.put("component_access_token", getPlatFormTokenAccessor().getAccessToken());
		String urlLocation = getAuthorizerInfoUrl.replace(params);
		String postContent = PlatFormMessage.wrapGetAuthorizerInfo(App.Info.id, authAppId);
		String respJson = HttpUtils.post(urlLocation, postContent);
		return JSON.parseObject(respJson, PlatFormGetAuthorizerInfoResult.class);
	}

	@Override
	public PlatFormGetAuthorizerOptionResult getAuthorizerOption(String authAppId, String optionName) {

		Map<String, Object> params = new HashMap<>();
		params.put("component_access_token", getPlatFormTokenAccessor().getAccessToken());
		String urlLocation = getAuthorizerOptionUrl.replace(params);
		String postContent = PlatFormMessage.wrapGetAuthorizerOption(App.Info.id, authAppId, optionName);
		String respJson = HttpUtils.post(urlLocation, postContent);
		return JSON.parseObject(respJson, PlatFormGetAuthorizerOptionResult.class);
	}

	@Override
	public PlatFormSetAuthorizerOptionResult setAuthorizerOption(String authAppId, String optionName,
			String optionValue) {

		Map<String, Object> params = new HashMap<>();
		params.put("component_access_token", getPlatFormTokenAccessor().getAccessToken());
		String urlLocation = setAuthorizerOptionUrl.replace(params);
		String postContent = PlatFormMessage.wrapSetAuthorizerOption(App.Info.id, authAppId, optionName, optionValue);
		String respJson = HttpUtils.post(urlLocation, postContent);
		return JSON.parseObject(respJson, PlatFormSetAuthorizerOptionResult.class);
	}

}
