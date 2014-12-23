/**
 * WeiXin
 * @title UploadFileResult.java
 * @package com.chn.wx.vo
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月18日-下午4:12:19
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class UploadFileResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class UploadFileResult {

    @JSONField(name="errcode") private String errcode;
    @JSONField(name="errmsg") private String errmsg;
    
    @JSONField(name="type") private String type;
    @JSONField(name="media_id") private String mediaId;
    @JSONField(name="created_at") private long createdAt;
    
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMediaId() {
        return mediaId;
    }
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    public long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
    
}
