package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryUserGroupResult extends BasicResult {

    private static final long serialVersionUID = -5596685310697861789L;
    
    @JSONField(name="groupid") private String groupid;
    
    public String getGroupid() {
        return groupid;
    }
    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
    
}
