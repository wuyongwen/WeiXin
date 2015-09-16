/**
 * WeiXin
 * @title QueryGroupsResult.java
 * @package com.chn.wx.vo.result
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月23日-下午6:17:47
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.vo.result;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.chn.wx.vo.Group;

/**
 * @class QueryGroupsResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class QueryGroupsResult {

    @JSONField(name="errcode") private String errcode;
    @JSONField(name="errmsg") private String errmsg;
    @JSONField(name="groups") private List<Group> groups;
    
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
    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    
}
