package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

public class ShortUrlResult {
    
    @JSONField(name="errcode") private String errcode;
    @JSONField(name="errmsg") private String errmsg;
    @JSONField(name="short_url") private String shortUrl;
    
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
    public String getShortUrl() {
        return shortUrl;
    }
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
    
}
