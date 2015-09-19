package com.chn.wx.invocation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.chn.common.HttpUtils;
import com.chn.common.StringTemplate;
import com.chn.wx.dto.App;
import com.chn.wx.template.PlatFormMessage;
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
    
    private static StringTemplate getPreAuthCodeUrl = StringTemplate.compile(WeiXinURL.PLATFORM_GET_PRE_AUTHCODE);
    private static long expireTime;
    private static String preAuthCode;
    
    public static String getPreAuthCode() {
        
        if(System.currentTimeMillis() < expireTime) return preAuthCode;
        
        Map<String, Object> params = new HashMap<>();
        params.put("component_access_token", PlatFormTokenAccessor.getAccessToken());
        String urlLocation = getPreAuthCodeUrl.replace(params);
        
        PlatFormGetPreAuthCodeResult result = null;
        try {
            String respJson = HttpUtils.post(urlLocation, PlatFormMessage.wrapGetPreAuthCode(App.Info.id));
            result = JSON.parseObject(respJson, PlatFormGetPreAuthCodeResult.class);
        } catch (Exception e) {
            log.error("请求 PreAuthCode 失败，继续采用之前 AuthCode！", e);
            return preAuthCode;
        }
        preAuthCode = result.getPreAuthCode();
        expireTime = System.currentTimeMillis() + result.getExpiresIn() * 900;
        log.info("更新 PreAuthCode：" + preAuthCode);
        return preAuthCode;
    }
    
}
