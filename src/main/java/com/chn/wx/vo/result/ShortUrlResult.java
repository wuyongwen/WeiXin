package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

public class ShortUrlResult extends BasicResult {
    
    private static final long serialVersionUID = -1241041155300095624L;
    
    @JSONField(name="short_url") private String shortUrl;
    
    public String getShortUrl() {
        return shortUrl;
    }
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
    
}
