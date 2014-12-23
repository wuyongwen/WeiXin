/**
 * WeiXin
 * @title QueryUserGroupResult.java
 * @package com.chn.wx.vo.result
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月23日-下午6:20:40
 * @version V1.0
 * Copyright (c) 2014 ChineseAll.com All Right Reserved
 */
package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @class QueryUserGroupResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class QueryUserGroupResult {

    @JSONField(name="errcode") private String errcode;
    @JSONField(name="errmsg") private String errmsg;
    @JSONField(name="groupid") private String groupid;
    
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
    public String getGroupid() {
        return groupid;
    }
    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
    
}
