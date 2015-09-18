package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatFormAccessTokenResult {

    @JSONField(name="component_access_token") private String componentAccessToken;
    @JSONField(name="expires_in") private Integer expiresIn;
    
    public String getComponentAccessToken() {
        return componentAccessToken;
    }
    public void setComponentAccessToken(String componentAccessToken) {
        this.componentAccessToken = componentAccessToken;
    }
    public Integer getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
    
}
