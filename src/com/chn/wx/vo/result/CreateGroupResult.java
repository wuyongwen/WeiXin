/**
 * WeiXin
 * @title CreateGroupResult.java
 * @package com.chn.wx.vo
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014年12月23日-下午6:10:20
 * @version V1.0
 * All Right Reserved
 */
package com.chn.wx.vo.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.chn.wx.vo.Group;

/**
 * @class CreateGroupResult
 * @author lzxz1234
 * @description 
 * @version v1.0
 */
public class CreateGroupResult {

    @JSONField(name="errcode") private String errcode;
    @JSONField(name="errmsg") private String errmsg;
    @JSONField(name="group") private Group group;
    
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
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    
}
