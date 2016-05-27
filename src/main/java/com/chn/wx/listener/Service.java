package com.chn.wx.listener;

import com.chn.wx.dto.Context;

/**
 * @class Service
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public interface Service {

    public static final String DEFAULT_RETURN = "";
    public static final String SUCCESS_RETURN = "success";
    // 全网发布自动化测试 appId
    public static final String AUTO_TEST_APPID = "wx570bc396a51b8ff8";
    // 全网发布自动化测试公众号userName
    public static final String AUTO_TEST_USERNAME = "gh_3c884a361561";
    /**
     * @description service
     * @param req
     * @param resp
     * @return 
    */
    public String doService(Context context) throws Exception;
    
}
