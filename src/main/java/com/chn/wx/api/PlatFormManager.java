package com.chn.wx.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.chn.common.HttpUtils;
import com.chn.common.Refresher;
import com.chn.common.StringTemplate;
import com.chn.wx.dto.App;
import com.chn.wx.template.PlatFormMessage;
import com.chn.wx.vo.result.PlatFormGetAuthInfoResult;
import com.chn.wx.vo.result.PlatFormGetPreAuthCodeResult;

/**
 * 公众号第三方平台可用主动调用
 * @class PlatFormManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class PlatFormManager {

    private static Logger log = Logger.getLogger(PlatFormManager.class);
    
    private static StringTemplate getAuthInfoUrl = StringTemplate.compile(WeiXinURL.PLATFORM_GET_AUTHINFO);
    
    private static Refresher<String> preAuthRefresher = new PreAuthRefresher();
    
    /**
     * 该API用于获取预授权码。预授权码用于公众号授权时的第三方平台方安全验证。
     * @return 
     */
    public static String getPreAuthCode() {
        
        return preAuthRefresher.get();
    }
    
    /**
     * 该API用于使用授权码换取授权公众号的授权信息，并换取authorizer_access_token和
     * authorizer_refresh_token。 授权码的获取，需要在用户在第三方平台授权页中完成授
     * 权流程后，在回调URI中通过URL参数提供给第三方平台方。
     * @param appId
     * @param authCode
     * @return 
     */
    public static PlatFormGetAuthInfoResult getAuthInfo(String appId, String authCode) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("component_access_token", PlatFormTokenAccessor.getAccessToken());
        String urlLocation = getAuthInfoUrl.replace(params);
        String postContent = PlatFormMessage.wrapGetAuthInfo(appId, authCode);
        String respJson = HttpUtils.post(urlLocation, postContent);
        return JSON.parseObject(respJson, PlatFormGetAuthInfoResult.class);
    }
    
    private static class PreAuthRefresher extends Refresher<String> {
        
        private StringTemplate getPreAuthCodeUrl = StringTemplate.compile(WeiXinURL.PLATFORM_GET_PRE_AUTHCODE);
        private long expireTime;
        
        @Override
        public String refresh() {
            
            Map<String, Object> params = new HashMap<>();
            params.put("component_access_token", PlatFormTokenAccessor.getAccessToken());
            String urlLocation = getPreAuthCodeUrl.replace(params);
            
            PlatFormGetPreAuthCodeResult result = null;
            try {
                String respJson = HttpUtils.post(urlLocation, PlatFormMessage.wrapGetPreAuthCode(App.Info.id));
                result = JSON.parseObject(respJson, PlatFormGetPreAuthCodeResult.class);
            } catch (Exception e) {
                log.error("请求 PreAuthCode 失败，继续采用之前 AuthCode！", e);
                return current;
            }
            expireTime = System.currentTimeMillis() + result.getExpiresIn() * 900;
            log.info("更新 PreAuthCode：" + result.getPreAuthCode());
            return result.getPreAuthCode();
        }

        @Override
        public boolean isExpired() {
            
            return System.currentTimeMillis() > expireTime;
        }
        
    }
}
