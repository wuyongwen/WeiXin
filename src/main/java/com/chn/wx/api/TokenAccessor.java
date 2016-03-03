﻿package com.chn.wx.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.chn.common.Assert;
import com.chn.common.HttpUtils;
import com.chn.common.Refresher;
import com.chn.common.StringTemplate;
import com.chn.wx.api.exception.ErrorUtils;
import com.chn.wx.api.exception.WxErrorException;
import com.chn.wx.dto.App;
import com.chn.wx.vo.result.AccessTokenResult;

/**
 * 公众号 AccessToken 管理器
 * 
 * @class TokenAccessor
 * @author lzxz1234
 * @description
 * @version v1.0
 */
public class TokenAccessor {

	private static Logger log = Logger.getLogger(TokenAccessor.class);

	private static StringTemplate accessTokenUrl = StringTemplate.compile(WeiXinURL.GET_TOKEN);
	private static TokenAccessorRefresher refresher = new TokenAccessorRefresher();

	public static synchronized String getAccessToken() throws WxErrorException {

		return refresher.get();
	}

	private static class TokenAccessorRefresher extends Refresher<String> {

		private long expireTime;

		@Override
		public String refresh() throws WxErrorException {

			Map<String, Object> params = new HashMap<>();
			params.put("appId", App.Info.id);
			params.put("appSecret", App.Info.secret);
			String urlLocation = accessTokenUrl.replace(params);
			AccessTokenResult result = null;
			String respJson = HttpUtils.get(urlLocation);
			result = JSON.parseObject(respJson, AccessTokenResult.class);
			log.debug("公众号 AccessToken获取: "+result);
			ErrorUtils.checkWXError(result);
			
			Assert.notNull(result.getAccessToken(), "获取Token失败[%s][%s]", result.getErrcode(), result.getErrmsg());
			// 900 是 1000 毫秒的 0.9 倍，百分十的提前量更新 Token
			expireTime = System.currentTimeMillis() + result.getExpiresIn() * 900;
			log.info("更新AccessToken：" + result.getAccessToken());
			return result.getAccessToken();
		}

		@Override
		public boolean isExpired() {

			return System.currentTimeMillis() > expireTime;
		}

	}

}
