package com.chn.wx.api;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.chn.common.HttpUtils;
import com.chn.common.Refresher;
import com.chn.wx.dto.App;
import com.chn.wx.template.PlatFormMessage;
import com.chn.wx.vo.result.PlatFormAccessTokenResult;

/**
 * 公众号第三方公众平台的 AccessToken 管理器
 * @class PlatFormTokenAccessor
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class PlatFormTokenAccessor {
    
    private static Logger log = Logger.getLogger(PlatFormTokenAccessor.class);
    private static PlatFormTokenRefresher refresher = new PlatFormTokenRefresher();
    
    public static synchronized String getAccessToken() {
        
        return refresher.get();
    }
    
    public static void updatePlatFormVerifyTicket(String componentVerifyTicket) {
        
        refresher.componentVerifyTicket = componentVerifyTicket;
    }
    
    private static class PlatFormTokenRefresher extends Refresher<String> {
        
        protected String componentVerifyTicket;
        private long expireTime;
        private String platformTokenUrl = WeiXinURL.GET_PLATFORM_TOKEN;
        @Override
        public String refresh() {
            PlatFormAccessTokenResult result = null;
            try {
                String postJson = PlatFormMessage.wrapGetAccessToken(App.Info.id, App.Info.secret, componentVerifyTicket);
                String respJson = HttpUtils.post(platformTokenUrl, postJson);
                result = JSON.parseObject(respJson, PlatFormAccessTokenResult.class);
            } catch (Exception e) {
                log.error("请求 AccessToken 失败，继续采用之前 Token！", e);
                return current;
            }
            expireTime = System.currentTimeMillis() + result.getExpiresIn() * 900;
            return result.getComponentAccessToken();
        }

        @Override
        public boolean isExpired() {
            return System.currentTimeMillis() > expireTime;
        }
        
    }
    
}
