package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatFormPreAuthCodeResult {

    @JSONField(name="pre_auth_code") private String preAuthCode;
    @JSONField(name="expires_in") private Integer expiresIn;
    
    public String getPreAuthCode() {
        return preAuthCode;
    }
    public void setPreAuthCode(String preAuthCode) {
        this.preAuthCode = preAuthCode;
    }
    public Integer getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
    
}
