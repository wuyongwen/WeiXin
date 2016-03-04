/**
 * WeiXin
 * @title AppInfo.java
 * @package com.chn.wx.dto
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2015年4月27日-下午9:33:38
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.dto;

import com.chn.common.Cfg;

/**
 * @class AppInfo
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class App {

    private static Cfg cfg = Cfg.getClassPathCfg("/weixin.properties");
    
    public static String getConfig(String key) {
        
        return cfg.get(key);
    }
    
    public static class Info {
        
        /**
         * id: appId
         */
        public static final String id;
        public static final String name;
        public static final String secret;
        public static final String aesKey;
        public static final String token;
        public static final String loginedUrl;
        
        public static final String defaultTicket;
        
        static {
            id = cfg.get("weixin.app.id");
            name = cfg.get("weixin.app.name");
            secret = cfg.get("weixin.app.secret");
            aesKey = cfg.get("weixin.app.aeskey");
            token = cfg.get("weixin.app.token");
            loginedUrl = cfg.get("weixin.thirdparty.logined.redirect");
            
            defaultTicket = cfg.get("weixin.default.componentverifyticket");
        }
    }
}
