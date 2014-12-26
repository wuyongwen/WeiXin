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
import com.chn.wx.vo.result.QueryFollowerResult;
import com.chn.wx.vo.result.QueryUserInfoResult;

/**
 * @class UserManager
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class UserManager {

    private static StringTemplate remarkUserUrl = compile(WeiXinURL.REMARK_USER);
    private static StringTemplate queryUserInfoUrl = compile(WeiXinURL.QUERY_USER_INFO);
    private static StringTemplate queryFollowerUrl = compile(WeiXinURL.QUERY_FOLLOWER);
    
    /**
     * @description 修改用户备注名
     * @param userId
     * @param remark
     * @return 
    */
    public static BasicResult remarkUser(String userId, String remark) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        String urlLocation = remarkUserUrl.replace(params);
        String respString = HttpUtils.post(urlLocation, UserMessage.wrapRemarkUser(userId, remark));
        return JSON.parseObject(respString, BasicResult.class);
    }
    
    /**
     * @description 获取用户基本信息（包括UnionID机制）
     * @param userId
     * @return 
    */
    public static QueryUserInfoResult queryUserInfo(String userId) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        params.put("openid", userId);
        String urlLocation = queryUserInfoUrl.replace(params);
        String respString = HttpUtils.get(urlLocation);
        return JSON.parseObject(respString, QueryUserInfoResult.class);
    }
    
    /**
     * @description 获取关注者列表
     * @param nextOpenId
     * @return 
    */
    public static QueryFollowerResult queryFollowers(String nextOpenId) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("accessToken", TokenAccessor.getAccessToken());
        params.put("next_openid", nextOpenId == null ? "" : nextOpenId);
        String urlLocation = queryFollowerUrl.replace(params);
        String respString = HttpUtils.get(urlLocation);
        return JSON.parseObject(respString, QueryFollowerResult.class);
    }
    
}
