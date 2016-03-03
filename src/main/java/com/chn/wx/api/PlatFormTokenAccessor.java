package com.chn.wx.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.chn.common.HttpUtils;
import com.chn.common.Refresher;
import com.chn.common.StringUtils;
import com.chn.wx.api.exception.ERROR;
import com.chn.wx.api.exception.ErrorUtils;
import com.chn.wx.api.exception.WxErrorException;
import com.chn.wx.dto.App;
import com.chn.wx.template.PlatFormMessage;
import com.chn.wx.vo.result.PlatFormAccessTokenResult;

/**
 * 公众号第三方公众平台的 AccessToken 管理器
 * 
 * @class PlatFormTokenAccessor
 * @author lzxz1234
 * @description
 * @version v1.0
 */
public class PlatFormTokenAccessor {

	private static Logger log = LoggerFactory.getLogger(PlatFormTokenAccessor.class);
	private static PlatFormTokenRefresher refresher = new PlatFormTokenRefresher();

	public static synchronized String getAccessToken() throws WxErrorException {

		return refresher.get();
	}

	public static void updatePlatFormVerifyTicket(String componentVerifyTicket) {

		refresher.componentVerifyTicket = componentVerifyTicket;
	}

	private static class PlatFormTokenRefresher extends Refresher<String> {

		protected String componentVerifyTicket;
		private long expireTime;
		private String platformTokenUrl = WeiXinURL.PLATFORM_GET_ACCESSTOKEN;

		@Override
		public String refresh() throws WxErrorException {
			PlatFormAccessTokenResult result = null;
			if (StringUtils.isEmpty(componentVerifyTicket)) {
				throw new WxErrorException(ERROR.TICKET.build());
			}
			String postJson = PlatFormMessage.wrapGetAccessToken(App.Info.id, App.Info.secret, componentVerifyTicket);
			String respJson = HttpUtils.post(platformTokenUrl, postJson);
			result = JSON.parseObject(respJson, PlatFormAccessTokenResult.class);
			
			ErrorUtils.checkWXError(result);
			
			log.debug("获取的component_access_token:{}", result);
			expireTime = System.currentTimeMillis() + result.getExpiresIn() * 900;
			return result.getComponentAccessToken();
		}

		@Override
		public boolean isExpired() {
			return System.currentTimeMillis() > expireTime;
		}

	}

}
