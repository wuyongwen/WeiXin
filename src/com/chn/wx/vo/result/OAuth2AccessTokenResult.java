/**
 * yiboche
 * @title OAuth2AccessTokenResult.java
 * @package com.chn.wx.vo.result
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2015年4月5日-下午6:46:07
 * @version V1.0
 * Copyright (c) 2015 ChineseAll.com All Right Reserved
 */
package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class OAuth2AccessTokenResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class OAuth2AccessTokenResult {
    
    @JSONField(name="errcode") private String errcode;
    @JSONField(name="errmsg") private String errmsg;
    
    @JSONField(name="access_token") private String accessToken;
    @JSONField(name="expires_in") private Integer expiresIn;
    @JSONField(name="refresh_token") private String refreshToken;
    @JSONField(name="openid") private String openId;
    @JSONField(name="scope") private String scope;
    @JSONField(name="unionid") private String unionId;
    
    public String getErrcode() {
        return errcode;
    }
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public Integer getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getUnionId() {
        return unionId;
    }
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
    
}
