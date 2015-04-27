/**
 * WeiXin
 * @title AppInfo.java
 * @package com.chn.wx.dto
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2015年4月27日-下午9:33:38
 * @version V1.0
 * Copyright (c) 2015 ChineseAll.com All Right Reserved
 */
package com.chn.wx.dto;

import org.apache.log4j.Logger;

import com.chn.common.Cfg;

/**
 * @class AppInfo
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class App {

    private static Logger log = Logger.getLogger(App.class);
    private static Cfg cfg = Cfg.getCfg("/weixin.properties");
    
    static {
        try {
            cfg = Cfg.getCfg("/weixin.properties");
        } catch (Exception e) {
            log.error("系统初始化失败", e);
            System.exit(0);
        }
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
        
        static {
            id = cfg.get("weixin.app.id");
            name = cfg.get("weixin.app.name");
            secret = cfg.get("weixin.app.secret");
            aesKey = cfg.get("weixin.app.aeskey");
            token = cfg.get("weixin.app.token");
            cfg = null;
        }
    }
}
