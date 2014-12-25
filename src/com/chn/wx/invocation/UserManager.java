/**
 * WeiXin
 * @title UserManager.java
 * @package com.chn.wx.invocation
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月25日-下午4:08:34
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.invocation;

import static com.chn.common.StringTemplate.compile;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.chn.common.StringTemplate;
import com.chn.wx.template.UserMessage;
import com.chn.wx.util.HttpUtils;
import com.chn.wx.vo.result.BasicResult;

/**
 * @class UserManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class UserManager {

    private static StringTemplate remarkUserUrl = compile(WeiXinURL.REMARK_USER);
    
    public static BasicResult remarkUser(String userId, String remark) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = remarkUserUrl.replace(params);
        String respString = HttpUtils.post(urlLocation, UserMessage.wrapRemarkUser(userId, remark));
        return JSON.parseObject(respString, BasicResult.class);
    }
    
}
